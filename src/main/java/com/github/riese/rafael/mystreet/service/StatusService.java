package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.repository.StatusRepository;

import org.springframework.stereotype.Service;

@Service
public class StatusService extends ServiceBase<Status, StatusRepository>{
    private StatusRepository statusRepository;

    protected StatusService(StatusRepository statusRepository) {
        super(statusRepository);
        this.statusRepository = statusRepository;
    }
}
