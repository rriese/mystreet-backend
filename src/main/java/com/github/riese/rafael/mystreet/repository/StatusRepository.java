package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Status;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatusRepository extends MongoRepository<Status, String> {
}
