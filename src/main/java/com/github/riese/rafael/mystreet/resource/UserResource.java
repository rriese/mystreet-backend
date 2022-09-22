package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Resource
    private UserService userService;
//    @Resource
//    private PasswordEncoder encoder;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/test")
    public User getNewUser() {
        return new User();
    }

    @PostMapping("/")
    public User save(@RequestBody User user) throws Exception {
//        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PutMapping("/")
    public User update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @DeleteMapping("/{email}")
    public boolean delete(@PathVariable String email) {
        return userService.delete(email);
    }
}
