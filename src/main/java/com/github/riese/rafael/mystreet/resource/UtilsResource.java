package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.service.UtilsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utils")
public class UtilsResource {
    @Resource
    UtilsService utilsService;

    @GetMapping("/userrole")
    public String getUserRole() {
        return utilsService.getAuthentication().getAuthorities().stream().map(c ->
                c.getAuthority()).collect(Collectors.toList()).get(0);
    }
}
