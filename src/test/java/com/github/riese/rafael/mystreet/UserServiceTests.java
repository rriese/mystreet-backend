package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.model.Status;
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
	static String testId;

	@BeforeAll
	static void initTest() {
		testId = null;
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
			 testId = userCreated.getId();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		assertNotNull(userCreated);
	}

	@Test
	@Order(2)
	void findUserCreated() {
		var user = userService.findById(testId);
		assertNotNull(user);
	}

	@Test
	@Order(3)
	void updateUser() {
		var user = new User();
		user.setId(testId);
		user.setName("User test updated");
		user.setCpfCnpj("111");
		user.setEmail("testUpdated@test.com");
		user.setPassword("1234");

		User userUpdated = null;

		try {
			userUpdated = userService.update(user).getBody();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		assertNotNull(userUpdated);
	}

	@Test
	@Order(4)
	void findAllUsers() {
		var users = userService.findAll().getBody();
		assertTrue(users.size() > 0);
	}

	@Test
	@Order(5)
	void deleteUser() {
		var isUserDeleted = userService.delete(testId).getBody();
		assertTrue(isUserDeleted);
	}

}
