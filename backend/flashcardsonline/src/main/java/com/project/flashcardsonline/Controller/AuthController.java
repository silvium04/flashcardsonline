package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.repositories.UserRepository;
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

@RestController
@RequestMapping("/api")
public class AuthController {
	private final AuthenticationManager authManager;
	private final UserRepository userRepo;

	public AuthController(AuthenticationManager authManager, UserRepository userRepo) {
		this.authManager = authManager;
		this.userRepo = userRepo;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication authentication = authManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok("Login successful");
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
}
