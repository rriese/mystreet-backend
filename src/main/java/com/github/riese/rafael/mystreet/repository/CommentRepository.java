package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByClaimId(String claimId);
}
