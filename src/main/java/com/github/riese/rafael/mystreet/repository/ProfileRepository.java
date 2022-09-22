package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}
