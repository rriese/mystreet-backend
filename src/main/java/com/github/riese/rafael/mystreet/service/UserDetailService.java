package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.data.UserDetailData;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with email [" + email + "] not found!");
        }

        return new UserDetailData(user);
    }
}
