package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.UserDTO;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getUserProfile(Principal principal) {
		Users user = userService.findByUsername(principal.getName());
		System.out.println(user.toString());
		UserDTO userDTO = new UserDTO(user.getUsername(), user.getFirstname(), user.getLastname());
		if (user != null) {
			return ResponseEntity.ok(userDTO);
		}
		return ResponseEntity.notFound().build();
	}


	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
}
