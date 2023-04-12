package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;
import com.github.riese.rafael.mystreet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {

    @Resource
    private PasswordEncoder encoder;

    @Resource
    private ProfileRepository profileRepository;

    @Resource
    private UserService userService;

    @PostMapping("/createcityhalluser")
    public ResponseEntity<User> createCityHallUser(@RequestBody User user) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));

        var profile = profileRepository.findByName("ROLE_CITY_HALL");

        if (profile.isPresent()) {
            user.setProfile(profile.get());
        } else {
            throw new Exception("Perfil de prefeitura inválido na base de dados!");
        }
        return userService.save(user);
    }

    @PostMapping("/createadminuser")
    public ResponseEntity<User> createAdminUser(@RequestBody User user) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));

        var profile = profileRepository.findByName("ROLE_ADMIN");

        if (profile.isPresent()) {
            user.setProfile(profile.get());
        } else {
            throw new Exception("Perfil de admin inválido na base de dados!");
        }
        return userService.save(user);
    }
    
}
