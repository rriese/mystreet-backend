package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.*;
import com.github.riese.rafael.mystreet.repository.CommentRepository;
import com.github.riese.rafael.mystreet.repository.ImageRepository;
import com.github.riese.rafael.mystreet.repository.LikeRepository;
import com.github.riese.rafael.mystreet.repository.ResolutionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrphanService {
    @Resource
    ImageRepository imageRepository;
    @Resource
    CommentRepository commentRepository;
    @Resource
    LikeRepository likeRepository;
    @Resource
    ResolutionRepository resolutionRepository;
    @Resource
    CityService cityService;

    protected void deleteClaimOrphanCascade(String id) {
        //Images
        List<Image> images = imageRepository.findByClaimId(id);
        images.forEach(image -> {
            imageRepository.deleteById(image.getId());
        });

        //Comments
        List<Comment> comments = commentRepository.findByClaimId(id);
        comments.forEach(comment -> {
            commentRepository.deleteById(comment.getId());
        });

        //Likes
        List<Like> likes = likeRepository.findByClaimId(id);
        likes.forEach(like -> {
            likeRepository.deleteById(like.getId());
        });

        //Resolutions
        Resolution resolution = resolutionRepository.findByClaimId(id);
        if (resolution != null) {
            resolutionRepository.deleteById(resolution.getId());
        }
    }

    protected void deleteStateOrphanCascade(String id) {
        List<City> cities = cityService.findByStateId(id);

        cities.forEach(city -> {
            cityService.delete(city.getId());
        });
    }
}
