package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeResource {
    @Resource
    private LikeService likeService;
    @Resource
    private UtilsService utilsService;
    @Resource
    private UserService userService;
    @Resource
    private ClaimService claimService;

    @GetMapping("/")
    public ResponseEntity<List<Like>> getLikes() {
        return likeService.findAll();
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<List<Like>> getLikesByClaimId(@PathVariable("claimId") String claimId) {
        return likeService.getLikesByClaimId(claimId);
    }

    @PostMapping("/{claimId}")
    public ResponseEntity<Like> save(@PathVariable String claimId) throws Exception {
        Like like = new Like();
        like.setUser(userService.findById(utilsService.getCurrentUserId()).getBody());
        like.setClaim(claimService.findById(claimId).getBody());
        return likeService.save(like);
    }

    @PutMapping("/")
    public ResponseEntity<Like> update(@RequestBody Like like) throws Exception {
        return likeService.update(like);
    }

    @DeleteMapping("/byclaim/{claimId}")
    public ResponseEntity<Boolean> deleteByCurrentUserAndClaimId(@PathVariable String claimId) {
        List<Like> likes = likeService.getLikesByClaimId(claimId).getBody();
        String currentUserId = utilsService.getCurrentUserId();

        likes.forEach(like -> {
            if (like.getUser().getId().equals(currentUserId)) {
                likeService.delete(like.getId());
            }
        });
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return likeService.delete(id);
    }
}
