package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Image;
import com.github.riese.rafael.mystreet.repository.ImageRepository;

import org.springframework.stereotype.Service;

@Service
public class ImageService extends ServiceBase<Image, ImageRepository> {
    protected ImageService(ImageRepository imageRepository) {
        super(imageRepository);
    }
}
