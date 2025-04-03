package com.project.flashcardsonline;

import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataInitializer {

	private final UserService userService;

	public DataInitializer(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct
	public void init() {
		Users users = new Users("password", "user", "user","user");
		userService.createUser(users);
		System.out.println(users.toString());
	}
}
