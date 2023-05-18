package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.City;
import com.github.riese.rafael.mystreet.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CityRepository extends MongoRepository<City, String> {
    List<City> findByStateId(String stateId);
}
