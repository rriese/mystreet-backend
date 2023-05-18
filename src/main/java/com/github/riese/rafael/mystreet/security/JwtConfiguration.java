package com.github.riese.rafael.mystreet.security;

import com.github.riese.rafael.mystreet.repository.UserRepository;
import com.github.riese.rafael.mystreet.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDetailService userDetailService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeHttpRequests().
                antMatchers(HttpMethod.POST, "/login").permitAll().
                antMatchers(HttpMethod.POST, "/api/user/").permitAll().
                antMatchers(HttpMethod.POST, "/api/user/resetpassword/**").permitAll().
                antMatchers("/api/changepassword/**").permitAll().
                antMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN").
                antMatchers("/api/admin/**").hasRole("ADMIN").
                antMatchers("/api/status/**").hasRole("ADMIN").
                antMatchers("/api/profile/**").hasRole("ADMIN").
                antMatchers("/api/chart/**").hasRole("ADMIN").
                antMatchers("/api/state/**").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/api/city/**").hasRole("ADMIN").
                antMatchers(HttpMethod.PUT, "/api/city/**").hasRole("ADMIN").
                antMatchers(HttpMethod.DELETE, "/api/city/**").hasRole("ADMIN").
                antMatchers("/api/report/excel/users/generate").hasRole("ADMIN").
                antMatchers("/api/report/export/**").permitAll().
                antMatchers(HttpMethod.POST, "/api/resolution/**").hasRole("CITY_HALL").
                antMatchers(HttpMethod.PUT, "/api/resolution/**").hasRole("CITY_HALL").
                antMatchers(HttpMethod.DELETE, "/api/resolution/**").hasRole("CITY_HALL").
                antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll().
                anyRequest().authenticated().
                and().
                addFilter(new JwtAuthenticationFilter(authenticationManager())).
                addFilter(new JwtValidationFilter(authenticationManager(), userRepository)).
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://mystreet-frontend-kt3nmfyba-rriese.vercel.app", "https://mystreet-frontend.vercel.app/"));
        corsConfiguration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
