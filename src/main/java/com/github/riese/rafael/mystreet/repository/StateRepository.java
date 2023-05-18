package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StateRepository extends MongoRepository<State, String> {
    State findByName(String name);
}
