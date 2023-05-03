package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {
    @Resource
    private CommentService commentService;
    @Resource
    private ClaimService claimService;
    @Resource
    private UtilsService utilsService;
    @Resource
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getComments() {
        return commentService.findAll();
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<List<Comment>> getLikesByClaimId(@PathVariable("claimId") String claimId) {
        return commentService.getCommentsByClaimId(claimId);
    }

    @PostMapping("/{claimId}")
    public ResponseEntity<Comment> save(@PathVariable String claimId, @RequestBody Comment comment) throws Exception {
        comment.setUser(userService.findById(utilsService.getCurrentUserId()).getBody());
        comment.setClaim(claimService.findById(claimId).getBody());
        return commentService.save(comment);
    }

    @PutMapping("/")
    public ResponseEntity<Comment> update(@RequestBody Comment comment) throws Exception {
        return commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return commentService.delete(id);
    }
}
