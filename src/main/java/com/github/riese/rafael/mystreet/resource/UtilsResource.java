package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.UserService;
import com.github.riese.rafael.mystreet.service.UtilsService;
import com.github.riese.rafael.mystreet.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utils")
public class UtilsResource {
    @Resource
    private UtilsService utilsService;

    @GetMapping("/userrole")
    public String getUserRole() {
        return utilsService.getAuthentication().getAuthorities().stream().map(c ->
                c.getAuthority()).collect(Collectors.toList()).get(0);
    }

    @GetMapping("/isadmin")
    public boolean isAdmin() {
        return this.getUserRole().equals("ROLE_ADMIN");
    }
}
