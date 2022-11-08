package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileResource {
    @Resource
    private ProfileService profileService;

    @GetMapping("/")
    public ResponseEntity<List<Profile>> getProfiles() {
        return profileService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Profile> save(@RequestBody Profile profile) throws Exception {
        return profileService.save(profile);
    }

    @PutMapping("/")
    public ResponseEntity<Profile> update(@RequestBody Profile profile) throws Exception {
        return profileService.update(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return profileService.delete(id);
    }
}
