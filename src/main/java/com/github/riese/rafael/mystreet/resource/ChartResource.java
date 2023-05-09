package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Chart;
import com.github.riese.rafael.mystreet.service.ChartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/chart")
public class ChartResource {
    @Resource
    private ChartService chartService;

    @GetMapping("/claimsperstatus")
    public ResponseEntity<Chart> getClaimsPerStatus() {
        return chartService.getClaimsPerStatus();
    }

    @GetMapping("/claimsperstate")
    public ResponseEntity<Chart> getClaimsPerState() {
        return chartService.getClaimsPerState();
    }

    @GetMapping("/claimspercity")
    public ResponseEntity<Chart> getClaimsPerCity() {
        return chartService.getClaimsPerCity();
    }

    @GetMapping("/usersperprofile")
    public ResponseEntity<Chart> getUsersPerProfile() {
        return chartService.getUsersPerProfile();
    }

    @GetMapping("/top10usersclaims")
    public ResponseEntity<Chart> getTopTenUsersClaims() {
        return chartService.getTopTenUsersClaim();
    }
}
