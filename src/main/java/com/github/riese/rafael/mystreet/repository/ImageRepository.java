package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Image;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
