package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByEmail(String login);
}
