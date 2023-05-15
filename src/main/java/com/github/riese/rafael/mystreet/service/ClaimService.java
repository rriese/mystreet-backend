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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimService extends ServiceBase<Claim, ClaimRepository> {
    @Resource
    private OrphanService orphanService;
    @Resource
    private StatusService statusService;
    @Resource
    private UtilsService utilsService;

    protected ClaimService(ClaimRepository claimRepository) {
        super(claimRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<List<Claim>> searchClaims(String term) {
        List<Claim> claims = this.findAll().getBody();
        List<Claim> result = new ArrayList<>();
        term = term.toUpperCase();

        for (Claim claim : claims) {
            if (claim.getState().toUpperCase().contains(term) ||
                claim.getCity().toUpperCase().contains(term) ||
                claim.getDistrict().toUpperCase().contains(term) ||
                claim.getTitle().toUpperCase().contains(term) ||
                claim.getDescription().toUpperCase().contains(term)) {
                result.add(claim);
            }
        }

        return ResponseEntity.ok().body(result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Claim> update(Claim claim) {
        Optional<Claim> entityOpt = repository.findById(claim.getId());

        if (entityOpt.isPresent()) {
            if (!utilsService.getAuthentication().getAuthorities().stream().map(c ->
                    c.getAuthority()).collect(Collectors.toList()).get(0).equals("ROLE_ADMIN") &&
                    !entityOpt.get().getUser().getId().equals(utilsService.getCurrentUserId())) {
                throw new RuntimeException("Você não tem autorização para atualizar uma reclamação que não é sua!");
            }

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
            if ((utilsService.getAuthentication() != null && !utilsService.getAuthentication().getAuthorities().stream().map(c -> c.getAuthority()).collect(Collectors.toList()).get(0).equals("ROLE_ADMIN")) &&
                    !entity.get().getUser().getId().equals(utilsService.getCurrentUserId())) {
                throw new RuntimeException("Você não tem autorização para deletar uma reclamação que não é sua!");
            }

            repository.deleteById(entity.get().getId());
            orphanService.deleteClaimOrphanCascade(id);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    public boolean closeClaim(Claim claim) {
        return this.updateStatus(claim, "Concluído");
    }

    public boolean openClaim(Claim claim) {
        return this.updateStatus(claim, "Pendente");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private boolean updateStatus(Claim claim, String statusName) {
        var status = statusService.findByStatusName(statusName);

        if (status != null) {
            claim.setStatus(status);
            repository.save(claim);
            return true;
        }
        return false;
    }
}
