package com.github.riese.rafael.mystreet.repository;

import com.github.riese.rafael.mystreet.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
