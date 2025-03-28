package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping("/createUser")
	public Users createUser() {
		Users user = new Users();
		user.setUsername("test");
		return userService.createUser(user);
	}

	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
}
