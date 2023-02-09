package com.github.riese.rafael.mystreet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtValidationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        authorizationHeader = authorizationHeader.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String user = JWT.require(Algorithm.HMAC512(JwtAuthenticationFilter.secret)).
                build().
                verify(token).
                getSubject();

        Collection<Profile> authorities = new ArrayList<>();
        var userObj = userRepository.findByEmail(user);

        if (userObj.isPresent()) {
            authorities.add(userObj.get().getProfile());
        }

        return user != null ? new UsernamePasswordAuthenticationToken(user, token, authorities) : null;
    }
}
