package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Resource
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/test")
    public User getNewUser() {
        return new User();
    }
}
