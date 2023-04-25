package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Image;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.service.ClaimService;
import com.github.riese.rafael.mystreet.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageResource {
    @Resource
    ImageService imageService;

    @Resource
    ClaimService claimService;

    @GetMapping("/")
    public ResponseEntity<List<Image>> getImages() {
        return imageService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Image> save(@RequestBody Image image) throws Exception {
        return imageService.save(image);
    }

    @PostMapping("/{claimId}")
    public ResponseEntity<Image> save(@PathVariable String claimId, @RequestBody byte[] image) throws Exception {
        Image img = new Image();
        img.setClaim(claimService.findById(claimId).getBody().get());
        img.setContent(image);
        return imageService.save(img);
    }

    @PutMapping("/")
    public ResponseEntity<Image> update(@RequestBody Image image) throws Exception {
        return imageService.update(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return imageService.delete(id);
    }
}
