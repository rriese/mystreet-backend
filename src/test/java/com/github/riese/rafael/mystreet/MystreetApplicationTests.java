package com.github.riese.rafael.mystreet;

import com.github.riese.rafael.mystreet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MystreetApplicationTests {

	@Resource
	UserService userService;

	@Test
	void getUsersNotEmpty() {
//		var users = userService.findAll();
//		assert/True(users.getBody().size() == 0);
	}

}
