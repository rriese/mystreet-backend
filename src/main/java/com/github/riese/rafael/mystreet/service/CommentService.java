package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.repository.CommentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceBase<Comment, CommentRepository> {
    @Resource
    private UtilsService utilsService;

    protected CommentService(CommentRepository commentRepository) {
        super(commentRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<List<Comment>> getCommentsByClaimId(String claimId) {
        return ResponseEntity.ok().body(repository.findByClaimId(claimId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Boolean> delete(String id) {
        Optional<Comment> entity = repository.findById(id);

        if (entity.isPresent()) {
            String currentUserId = utilsService.getCurrentUserId();

            if (currentUserId != null && !entity.get().getUser().getId().equals(currentUserId)) {
                throw new RuntimeException("Não é possível deletar comentários que não são seus!");
            }

            repository.deleteById(entity.get().getId());
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
