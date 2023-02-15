package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.Comment;
import com.github.riese.rafael.mystreet.model.Image;
import com.github.riese.rafael.mystreet.model.Like;
import com.github.riese.rafael.mystreet.service.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JoinedServicesTests {
    @Resource
    private StatusService statusService;
    @Resource
    private UserService userService;
    @Resource
    private ClaimService claimService;
    @Resource
    private LikeService likeService;
    @Resource
    private CommentService commentService;
    @Resource
    private ImageService imageService;
    static String claimId;
    static String likeId;
    static String commentId;
    static String imageId;

    @BeforeAll
    static void initTest() {
        claimId = null;
        likeId = null;
        commentId = null;
        imageId = null;
    }

    @Test
    @Order(1)
    void createClaim() {
        var claim = new Claim();
        claim.setTitle("Claim test");
        claim.setDescription("This is a claim test");
        claim.setCity("Jaraguá do Sul");
        claim.setDistrict("São luis");
        claim.setState("Santa Catarina");
        claim.setStatus(statusService.findById("63ed55f10256fd8902331b74").getBody().get());
        claim.setUser(userService.findById("63e41f086b02063c9d4f4c7b").getBody().get());

        Claim claimCreated = null;

        try {
            claimCreated = claimService.save(claim).getBody();
            claimId = claimCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(claimId);
    }

    @Test
    @Order(2)
    void findClaimCreated() {
        var claim = claimService.findById(claimId);
        assertNotNull(claim);
    }

    @Test
    @Order(3)
    void findAllClaims() {
        var claims = claimService.findAll().getBody();
        assertTrue(claims.size() > 0);
    }

    @Test
    @Order(4)
    void likeClaim() {
        var like = new Like();
        like.setUser(userService.findById("63e41f086b02063c9d4f4c7b").getBody().get());

        var claim = new Claim();
        claim.setId(claimId);
        like.setClaim(claim);

        Like likeCreated = null;

        try {
            likeCreated = likeService.save(like).getBody();
            likeId = likeCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(likeCreated);
    }

    @Test
    @Order(5)
    void deleteLike() {
        var isLikeDeleted = likeService.delete(likeId).getBody();
        assertTrue(isLikeDeleted);
    }

    @Test
    @Order(6)
    void createComment() {
        var comment = new Comment();
        comment.setDescription("LALALA");

        var claim = new Claim();
        claim.setId(claimId);
        comment.setClaim(claim);
        comment.setUser(userService.findById("63e41f086b02063c9d4f4c7b").getBody().get());

        Comment commentCreated = null;

        try {
            commentCreated = commentService.save(comment).getBody();
            commentId = commentCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(commentCreated);
    }

    @Test
    @Order(7)
    void deleteComment() {
        var isCommentDeleted = commentService.delete(commentId).getBody();
        assertTrue(isCommentDeleted);
    }

    @Test
    @Order(8)
    void createImage() {
        var image = new Image();

        var claim = new Claim();
        claim.setId(claimId);
        image.setClaim(claim);
        image.setContent(HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"));

        Image imageCreated = null;

        try {
            imageCreated = imageService.save(image).getBody();
            imageId = imageCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(imageCreated);
    }

    @Test
    @Order(9)
    void deleteImage() {
        var isImageDeleted = imageService.delete(imageId).getBody();
        assertTrue(isImageDeleted);
    }

    @Test
    @Order(10)
    void deleteClaim() {
        var isClaimDeleted = claimService.delete(claimId).getBody();
        assertTrue(isClaimDeleted);
    }
}
