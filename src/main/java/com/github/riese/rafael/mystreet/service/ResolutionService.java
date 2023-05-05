package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.Resolution;
import com.github.riese.rafael.mystreet.repository.ResolutionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResolutionService extends ServiceBase<Resolution, ResolutionRepository>{
    @Resource
    private ClaimService claimService;

    protected ResolutionService(ResolutionRepository resolutionRepository) {
        super(resolutionRepository);
    }

    public Resolution getResolutionsByClaimId(String claimId) {
        return repository.findByClaimId(claimId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Resolution> save(String claimId, Resolution resolution) throws Exception {
        Claim claim = claimService.findById(claimId).getBody();

        if (claim == null) {
            throw new RuntimeException("Reclamação não encontrada na base!");
        }
        resolution.setClaim(claim);
        claimService.closeClaim(claim);
        return super.save(resolution);
    }
}
