package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageResource {
    @Resource
    ImageService imageService;

    @GetMapping("/{claimId}/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String claimId, @PathVariable String imageId) {
        byte[] images = imageService.download(claimId, imageId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<List<String>> getImagesByClaim(@PathVariable String claimId) {
        return imageService.getImagesByClaimId(claimId);
    }

    @PostMapping("/{claimId}")
    public ResponseEntity save(@PathVariable String claimId, @RequestParam MultipartFile image) throws Exception {
        return imageService.upload(claimId, image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return imageService.delete(id);
    }
}
