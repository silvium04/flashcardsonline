package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.UserRepository;
import com.project.flashcardsonline.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
	private final AuthenticationManager authManager;
	private final UserRepository userRepo;
	private final UserService userService;

	public AuthController(AuthenticationManager authManager, UserRepository userRepo, UserService userService) {
		this.authManager = authManager;
		this.userRepo = userRepo;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication authentication = authManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok(Map.of("message", "Login successful"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login unsuccessful"));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		if (userService.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
		}
		if (user.getUsername() == null || user.getUsername().isEmpty() ||
			user.getPassword() == null || user.getPassword().isEmpty() ||
			user.getFirstname() == null || user.getFirstname().isEmpty() ||
			user.getLastname() == null || user.getLastname().isEmpty()) {
			return ResponseEntity.badRequest().body(Map.of("error", "Field cannot be empty"));
		}
		if (user.getPassword().length() < 8) {
			return ResponseEntity.badRequest().body(Map.of("error", "Password must be at least 8 characters long"));
		}
		userService.createUser(user);
		return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}
}
