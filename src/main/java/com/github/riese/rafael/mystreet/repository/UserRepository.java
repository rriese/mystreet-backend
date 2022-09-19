package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
