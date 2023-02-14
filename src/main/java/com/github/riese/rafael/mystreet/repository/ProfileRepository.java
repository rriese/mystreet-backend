package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Profile;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByName(String name);
}
