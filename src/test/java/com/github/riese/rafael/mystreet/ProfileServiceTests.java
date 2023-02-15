package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.service.ProfileService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileServiceTests {
    @Resource
    private ProfileService profileService;
    static String testId;

    @BeforeAll
    static void initTest() {
        testId = null;
    }

    @Test
    @Order(1)
    void createProfile() {
        var profile = new Profile();
        profile.setName("Profile test");

        Profile profileCreated = null;

        try {
            profileCreated = profileService.save(profile).getBody();
            testId = profileCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(testId);
    }

    @Test
    @Order(2)
    void findProfileCreated() {
        var profile = profileService.findById(testId);
        assertNotNull(profile);
    }

    @Test
    @Order(3)
    void updateProfile() {
        var profile = new Profile();
        profile.setId(testId);
        profile.setName("Profile test updated");

        Profile profileUpdated = null;

        try {
            profileUpdated = profileService.update(profile).getBody();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(profileUpdated);
    }

    @Test
    @Order(4)
    void findAllProfiles() {
        var profile = profileService.findAll().getBody();
        assertTrue(profile.size() > 0);
    }

    @Test
    @Order(5)
    void deleteProfile() {
        var isProfileDeleted = profileService.delete(testId).getBody();
        assertTrue(isProfileDeleted);
    }
}
