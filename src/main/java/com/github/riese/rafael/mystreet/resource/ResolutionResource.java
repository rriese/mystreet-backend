package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Resolution;
import com.github.riese.rafael.mystreet.service.ResolutionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/resolution")
public class ResolutionResource {
    @Resource
    private ResolutionService resolutionService;

    @GetMapping("/")
    public ResponseEntity<List<Resolution>> getResolutions() {
        return resolutionService.findAll();
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<Resolution> getResolutionByClaimId(@PathVariable String claimId) {
        return ResponseEntity.ok().body(resolutionService.getResolutionByClaimId(claimId));
    }

    @PostMapping("/{claimId}")
    public ResponseEntity<Resolution> save(@PathVariable String claimId, @RequestBody Resolution resolution) throws Exception {
        return resolutionService.save(claimId, resolution);
    }

    @PutMapping("/")
    public ResponseEntity<Resolution> update(@RequestBody Resolution resolution) throws Exception {
        return resolutionService.update(resolution);
    }

    @DeleteMapping("/{claimId}")
    public ResponseEntity<Boolean> deleteByClaimId(@PathVariable String claimId) {
        Resolution resolution = resolutionService.getResolutionByClaimId(claimId);

        if (resolution != null) {
            return resolutionService.deleteResolutionAndOpenClaim(resolution.getId(), claimId);
        }
        return ResponseEntity.ok().body(false);
    }
}
