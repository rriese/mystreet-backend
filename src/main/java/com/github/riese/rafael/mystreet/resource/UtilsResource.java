package com.github.riese.rafael.mystreet.resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utils")
public class UtilsResource {

    @GetMapping("/userrole")
    public String getUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(c ->
                c.getAuthority()).collect(Collectors.toList()).get(0);
    }
}
