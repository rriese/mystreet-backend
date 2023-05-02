package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.repository.LikeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService extends ServiceBase<Like, LikeRepository> {
    protected LikeService(LikeRepository likeRepository) {
        super(likeRepository);
    }

    public ResponseEntity<List<Like>> getLikesByClaimId(String claimId) {
        return ResponseEntity.ok().body(repository.findByClaimId(claimId));
    }
}
