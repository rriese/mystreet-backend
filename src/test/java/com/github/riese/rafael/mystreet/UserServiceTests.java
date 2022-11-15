package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.ProfileService;
import com.github.riese.rafael.mystreet.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTests {

	@Resource
	UserService userService;
	@Resource
	ProfileService profileService;
	static String userId;

	@BeforeAll
	static void initTest() {
		userId = null;
	}

	@Test
	@Order(1)
	void createUser() {
		var user = new User();
		user.setName("User test");
		user.setCpfCnpj("9999999");
		user.setEmail("test@test.com");
		user.setPassword("123");

		var profile = profileService.findById("6328eac3c2daf66f2ab7984f").getBody();

		if (profile.isPresent()) {
			user.setProfile(profile.get());
		}

		User userCreated = null;

		try {
			 userCreated = userService.save(user).getBody();
			 userId = userCreated.getId();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		assertNotNull(userCreated);
	}

	@Test
	@Order(2)
	void findUserCreated() {
		var user = userService.findById(userId);
		assertNotNull(user);
	}

	@Test
	@Order(3)
	void findAllUsers() {
		var users = userService.findAll().getBody();
		assertTrue(users.size() > 0);
	}

	@Test
	@Order(4)
	void deleteUser() {
		var isUserDeleted = userService.delete(userId).getBody();
		assertTrue(isUserDeleted);
	}

}
