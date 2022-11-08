package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Claim;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClaimRepository extends MongoRepository<Claim, String> {
}
