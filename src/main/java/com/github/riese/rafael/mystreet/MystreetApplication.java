package com.github.riese.rafael.mystreet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing(modifyOnCreate = false)
public class MystreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystreetApplication.class, args);
	}

}
