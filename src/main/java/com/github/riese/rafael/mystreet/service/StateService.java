package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.State;
import com.github.riese.rafael.mystreet.repository.StateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StateService extends ServiceBase<State, StateRepository>{
    @Resource
    private OrphanService orphanService;
    protected StateService(StateRepository stateAndCityRepository) {
        super(stateAndCityRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public State findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> delete(String id) {
        orphanService.deleteStateOrphanCascade(id);
        return super.delete(id);
    }

}
