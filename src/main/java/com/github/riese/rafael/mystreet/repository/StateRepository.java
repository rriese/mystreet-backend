package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {
}
