package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.ProfileRepository;
import com.github.riese.rafael.mystreet.service.UserService;

import com.github.riese.rafael.mystreet.service.UtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Resource
    private UserService userService;

    @Resource
    private ProfileRepository profileRepository;

    @Resource
    private PasswordEncoder encoder;

    @Resource
    private UtilsService utilsService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/excludecityhall")
    public ResponseEntity<List<User>> getUsersExcludingCityHall() {
        List<User> users = userService.findAll().getBody();
        return ResponseEntity.ok().body(users.stream().filter(c -> !c.getProfile().getName().equals("ROLE_CITY_HALL")).collect(Collectors.toList()));
    }

    @GetMapping("/cityhall")
    public ResponseEntity<List<User>> getUsersCityHall() {
        List<User> users = userService.findAll().getBody();
        return ResponseEntity.ok().body(users.stream().filter(c -> c.getProfile().getName().equals("ROLE_CITY_HALL")).collect(Collectors.toList()));
    }

    @GetMapping("/currentuser")
    public ResponseEntity<User> getCurrentUser() {
        return userService.getCurrentUser(utilsService.getCurrentUserId());
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));

        var profile = profileRepository.findByName("ROLE_VISITOR");

        if (profile.isPresent()) {
            user.setProfile(profile.get());
        } else {
            throw new Exception("Perfil de visitante inválido na base de dados!");
        }

        return userService.save(user);
    }

    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        if (utilsService.getCurrentUserId().equals(id)) {
            throw new RuntimeException("Não é possível deletar você mesmo!");
        }
        return userService.delete(id);
    }
}
