package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.UserService;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {
//        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
