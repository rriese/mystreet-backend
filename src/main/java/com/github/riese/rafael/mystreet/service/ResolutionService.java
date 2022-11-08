package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Resolution;
import com.github.riese.rafael.mystreet.repository.ResolutionRepository;

import org.springframework.stereotype.Service;

@Service
public class ResolutionService extends ServiceBase<Resolution, ResolutionRepository>{
    protected ResolutionService(ResolutionRepository resolutionRepository) {
        super(resolutionRepository);
    }
}
