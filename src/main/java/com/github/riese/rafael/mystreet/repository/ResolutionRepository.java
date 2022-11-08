package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Resolution;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResolutionRepository extends MongoRepository<Resolution, String> {
}
