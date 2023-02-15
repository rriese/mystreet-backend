package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.model.Profile;
import com.github.riese.rafael.mystreet.model.Status;
import com.github.riese.rafael.mystreet.service.StatusService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatusServiceTests {

    @Resource
    StatusService statusService;
    static String testId;

    @BeforeAll
    static void initTest() {
        testId = null;
    }

    @Test
    @Order(1)
    void createStatus() {
        var status = new Status();
        status.setName("Status test");

        Status statusCreated = null;

        try {
            statusCreated = statusService.save(status).getBody();
            testId = statusCreated.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(testId);
    }

    @Test
    @Order(2)
    void findStatusCreated() {
        var status = statusService.findById(testId);
        assertNotNull(status);
    }

    @Test
    @Order(3)
    void updateStatus() {
        var status = new Status();
        status.setId(testId);
        status.setName("Status test updated");

        Status statusUpdated = null;

        try {
            statusUpdated = statusService.update(status).getBody();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertNotNull(statusUpdated);
    }

    @Test
    @Order(4)
    void findAllStatus() {
        var status = statusService.findAll().getBody();
        assertTrue(status.size() > 0);
    }

    @Test
    @Order(5)
    void deleteStatus() {
        var isStatusDeleted = statusService.delete(testId).getBody();
        assertTrue(isStatusDeleted);
    }

}
