package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
