package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.ResetPassword;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResetPasswordRepository extends MongoRepository<ResetPassword, String> {
    Optional<ResetPassword> findByToken(String token);
}
