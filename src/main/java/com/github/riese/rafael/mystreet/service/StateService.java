package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.State;
import com.github.riese.rafael.mystreet.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService extends ServiceBase<State, StateRepository>{
    protected StateService(StateRepository stateAndCityRepository) {
        super(stateAndCityRepository);
    }
}
