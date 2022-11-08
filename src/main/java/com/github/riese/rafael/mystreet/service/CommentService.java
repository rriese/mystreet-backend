package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.repository.CommentRepository;

import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceBase<Comment, CommentRepository> {
    protected CommentService(CommentRepository commentRepository) {
        super(commentRepository);
    }
}
