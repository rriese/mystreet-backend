package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.service.LikeService;
import com.github.riese.rafael.mystreet.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeResource {
    @Resource
    private LikeService likeService;

    @GetMapping("/")
    public ResponseEntity<List<Like>> getLikes() {
        return likeService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Like> save(@RequestBody Like like) throws Exception {
        return likeService.save(like);
    }

    @PutMapping("/")
    public ResponseEntity<Like> update(@RequestBody Like like) throws Exception {
        return likeService.update(like);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return likeService.delete(id);
    }
}
