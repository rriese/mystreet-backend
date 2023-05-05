package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.Resolution;
import com.github.riese.rafael.mystreet.repository.ResolutionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ResolutionService extends ServiceBase<Resolution, ResolutionRepository>{
    @Resource
    private ClaimService claimService;

    protected ResolutionService(ResolutionRepository resolutionRepository) {
        super(resolutionRepository);
    }

    public Resolution getResolutionByClaimId(String claimId) {
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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Resolution> update(Resolution resolution) throws Exception {
        Optional<Resolution> entityOpt = repository.findById(resolution.getId());

        if (entityOpt.isPresent()) {
            Resolution oldEntity = entityOpt.get();
            resolution.setClaim(oldEntity.getClaim());
            BeanUtils.copyProperties(resolution, oldEntity);

            try {
                repository.save(oldEntity);
            } catch (DuplicateKeyException dke) {
                throw new DuplicateKeyException("Chave duplicada na base!");
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
            return ResponseEntity.ok().body(oldEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> deleteResolutionAndOpenClaim(String resolutionId, String claimId) {
        Claim claim = claimService.findById(claimId).getBody();

        if (claim != null) {
            claimService.openClaim(claim);
        }
        return this.delete(resolutionId);
    }
}
