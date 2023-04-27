package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.repository.CommentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends ServiceBase<Comment, CommentRepository> {
    protected CommentService(CommentRepository commentRepository) {
        super(commentRepository);
    }

    public List<Comment> getCommentsByClaimId(String claimId) {
        return repository.findByClaimId(claimId);
    }
}
