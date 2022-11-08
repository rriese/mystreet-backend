package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.repository.ClaimRepository;

import org.springframework.stereotype.Service;

@Service
public class ClaimService extends ServiceBase<Claim, ClaimRepository> {
    protected ClaimService(ClaimRepository claimRepository) {
        super(claimRepository);
    }
}
