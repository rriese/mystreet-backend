package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.repository.StatusRepository;
import com.github.riese.rafael.mystreet.service.ClaimService;

import com.github.riese.rafael.mystreet.service.StatusService;
import com.github.riese.rafael.mystreet.service.UserService;
import com.github.riese.rafael.mystreet.service.UtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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

    @GetMapping("/myclaims")
    public ResponseEntity<List<Claim>> getMyClaims() {
        var claims = claimService.findAll();
        var myClaims = claims.getBody().stream().filter(c -> c.getUser().getId().equals(utilsService.getCurrentUserId())).collect(Collectors.toList());
        return ResponseEntity.ok().body(myClaims);
    }

    @PostMapping("/")
    public ResponseEntity<Claim> save(@RequestBody Claim claim) throws Exception {
        claim.setUser(userService.findById(utilsService.getCurrentUserId()).getBody().get());
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
