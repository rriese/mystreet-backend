package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.repository.StatusRepository;
import com.github.riese.rafael.mystreet.service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/claim")
public class ClaimResource {
    @Resource
    private ClaimService claimService;
    @Resource
    private UserService userService;
    @Resource
    private UtilsService utilsService;
    @Resource
    private StatusRepository statusRepository;

    @GetMapping("/")
    public ResponseEntity<List<Claim>> getClaims() {
        return claimService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> findById(@PathVariable String id) {
        return claimService.findById(id);
    }

    @GetMapping("/myclaims")
    public ResponseEntity<List<Claim>> getMyClaims() {
        var claims = claimService.findAll();
        var myClaims = claims.getBody().stream().filter(c -> c.getUser().getId().equals(utilsService.getCurrentUserId())).collect(Collectors.toList());
        return ResponseEntity.ok().body(myClaims);
    }

    @GetMapping("/cityhallclaims")
    public ResponseEntity<List<Claim>> getCityHallClaims() {
        User currentUser = userService.getCurrentUser(utilsService.getCurrentUserId()).getBody();

        if (currentUser != null && currentUser.getProfile().getName().equals("ROLE_CITY_HALL")) {
            var claims = claimService.findAll();
            var myClaims = claims.getBody().stream().filter(c -> c.getCity().equals(currentUser.getCity()) && c.getState().equals(currentUser.getState())).collect(Collectors.toList());
            return ResponseEntity.ok().body(myClaims);
        }
        return this.getClaims();
    }

    @PostMapping("/")
    public ResponseEntity<Claim> save(@RequestBody Claim claim) throws Exception {
        claim.setUser(userService.findById(utilsService.getCurrentUserId()).getBody());
        claim.setStatus(statusRepository.findByName("Pendente").get());
        return claimService.save(claim);
    }

    @PutMapping("/")
    public ResponseEntity<Claim> update(@RequestBody Claim claim) throws Exception {
        return claimService.update(claim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return claimService.delete(id);
    }
}
