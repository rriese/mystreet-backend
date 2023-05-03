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
        return ResponseEntity.ok().body(resolutionService.getResolutionsByClaimId(claimId));
    }

    @PostMapping("/")
    public ResponseEntity<Resolution> save(@RequestBody Resolution resolution) throws Exception {
        return resolutionService.save(resolution);
    }

    @PutMapping("/")
    public ResponseEntity<Resolution> update(@RequestBody Resolution resolution) throws Exception {
        return resolutionService.update(resolution);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return resolutionService.delete(id);
    }
}
