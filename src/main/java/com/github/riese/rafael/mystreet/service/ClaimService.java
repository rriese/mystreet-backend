package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.*;
import com.github.riese.rafael.mystreet.repository.ClaimRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ClaimService extends ServiceBase<Claim, ClaimRepository> {
    @Resource
    private OrphanService orphanService;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> delete(String id) {
        Optional<Claim> entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.deleteById(entity.get().getId());
            orphanService.deleteClaimOrphanCascade(id);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
