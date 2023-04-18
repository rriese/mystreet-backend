package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.repository.ClaimRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClaimService extends ServiceBase<Claim, ClaimRepository> {
    protected ClaimService(ClaimRepository claimRepository) {
        super(claimRepository);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Claim> update(Claim claim) {
        Optional<Claim> entityOpt = repository.findById(claim.getId());

        if (entityOpt.isPresent()) {
            Claim oldEntity = entityOpt.get();
            claim.setUser(oldEntity.getUser());
            claim.setStatus(oldEntity.getStatus());
            BeanUtils.copyProperties(claim, oldEntity);
            repository.save(oldEntity);
            return ResponseEntity.ok().body(oldEntity);
        }
        return ResponseEntity.notFound().build();
    }
}
