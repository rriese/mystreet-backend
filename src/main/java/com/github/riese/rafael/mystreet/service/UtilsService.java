package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.model.Image;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.Resolution;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UtilsService {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserId() {
        return (String)this.getAuthentication().getDetails();
    }

}
