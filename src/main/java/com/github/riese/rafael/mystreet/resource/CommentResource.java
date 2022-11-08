package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.service.CommentService;
import com.github.riese.rafael.mystreet.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {
    @Resource
    private CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getComments() {
        return commentService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Comment> save(@RequestBody Comment comment) throws Exception {
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
