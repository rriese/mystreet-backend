package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.repository.StatusRepository;

import org.springframework.stereotype.Service;

@Service
public class StatusService extends ServiceBase<Status, StatusRepository>{
    protected StatusService(StatusRepository statusRepository) {
        super(statusRepository);
    }

    public Status findByStatusName(String name) {
        var status = repository.findByName(name);
        return status.isPresent() ? status.get() : null;
    }
}
