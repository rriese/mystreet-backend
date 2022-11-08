package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Like;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, String> {
}
