package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Image;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> findByIdAndClaimId(String Id, String claimId);
    List<Image> findByClaimId(String claimId);
}
