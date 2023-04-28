package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Image;
import com.github.riese.rafael.mystreet.repository.ImageRepository;

import com.github.riese.rafael.mystreet.util.ImageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService extends ServiceBase<Image, ImageRepository> {
    @Resource
    private ClaimService claimService;

    protected ImageService(ImageRepository imageRepository) {
        super(imageRepository);
    }

    public ResponseEntity<Image> upload(String claimId, MultipartFile image) throws Exception {
        Image img = new Image();
        img.setClaim(claimService.findById(claimId).getBody());
        img.setContent(ImageUtil.compressImage(image.getBytes()));
        return this.save(img);
    }

    public byte[] download(String claimId, String imageId) {
        Optional<Image> imageData = repository.findByIdAndClaimId(imageId, claimId);
        return ImageUtil.decompressImage(imageData.get().getContent());
    }

    public List<Image> getImagesByClaimId(String claimId) {
        return repository.findByClaimId(claimId);
    }

    public ResponseEntity<List<String>> getImagesIdByClaimId(String claimId) {
        return ResponseEntity.ok().body(this.getImagesByClaimId(claimId).stream().map(c -> c.getId()).collect(Collectors.toList()));
    }
}
