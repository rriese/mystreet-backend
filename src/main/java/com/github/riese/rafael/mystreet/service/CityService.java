package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.City;
import com.github.riese.rafael.mystreet.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService extends ServiceBase<City, CityRepository> {
    protected CityService(CityRepository cityRepository) {
        super(cityRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<City> findByStateId(String stateId) {
        return repository.findByStateId(stateId);
    }
}
