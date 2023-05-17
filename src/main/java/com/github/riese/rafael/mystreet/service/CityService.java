package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.City;
import com.github.riese.rafael.mystreet.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService extends ServiceBase<City, CityRepository> {
    protected CityService(CityRepository cityRepository) {
        super(cityRepository);
    }
}
