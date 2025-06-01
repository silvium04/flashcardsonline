package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.LoginRequest;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.UserRepository;
import com.project.flashcardsonline.security.JwtUtil;
import com.project.flashcardsonline.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

	private final UserRepository userRepo;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final JwtUtil jwtUtil;

	public AuthController(UserRepository userRepo, UserService userService, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Users user = userRepo.findByUsername(loginRequest.getUsername());
		if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
		}

		String jwt = jwtUtil.generateToken(user.getUsername());

		return ResponseEntity.ok(Map.of("token", jwt));

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		try {
			System.out.println("hallo from register" + user.toString());
			if (userService.existsByUsername(user.getUsername())) {
				System.out.println("Username already exists: " + user.getUsername());
				return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
			}


			Users savedUser = userService.createUser(user);
			if (savedUser == null || savedUser.getUserId() == null) {
				System.out.println("Failed to save user: " + user.getUsername());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(Map.of("error", "Failed to save user"));
			}

			System.out.println("User registered: " + savedUser.getUsername() + " with ID: " + savedUser.getUserId());
			return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", savedUser.getUserId()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Registration failed: " + e.getMessage()));
		}
	}

}
