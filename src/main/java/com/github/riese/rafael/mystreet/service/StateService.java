package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.State;
import com.github.riese.rafael.mystreet.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService extends ServiceBase<State, StateRepository>{
    protected StateService(StateRepository stateAndCityRepository) {
        super(stateAndCityRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public State findByName(String name) {
        return repository.findByName(name);
    }
}
