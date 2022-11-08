package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.service.ClaimService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/claim")
public class ClaimResource {
    @Resource
    private ClaimService claimService;

    @GetMapping("/")
    public ResponseEntity<List<Claim>> getClaims() {
        return claimService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Claim> save(@RequestBody Claim claim) throws Exception {
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
