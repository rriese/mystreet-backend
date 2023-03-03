package com.github.riese.rafael.mystreet.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserId() {
        return (String)this.getAuthentication().getDetails();
    }

//    public String getCurrentUserEmail() {
//        return (String)this.getAuthentication().getPrincipal();
//    }
}
