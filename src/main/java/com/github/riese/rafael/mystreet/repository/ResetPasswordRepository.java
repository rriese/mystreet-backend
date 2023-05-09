package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.ResetPassword;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ResetPasswordRepository extends MongoRepository<ResetPassword, String> {
    Optional<ResetPassword> findByToken(String token);
    Optional<List<ResetPassword>> findByEmail(String email);
}
