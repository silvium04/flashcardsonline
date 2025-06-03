package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.LoginRequest;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.UserRepository;
import com.project.flashcardsonline.security.JwtUtil;
import com.project.flashcardsonline.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private UserRepository userRepo;
    private UserService userService;
    private JwtUtil jwtUtil;
    private AuthController authController;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        userService = mock(UserService.class);
        jwtUtil = mock(JwtUtil.class);
        authController = new AuthController(userRepo, userService, jwtUtil);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void testLoginSuccess() {
        String rawPassword = "secret";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        Users mockUser = new Users();
        mockUser.setUsername("user1");
        mockUser.setPassword(hashedPassword);

        when(userRepo.findByUsername("user1")).thenReturn(mockUser);
        when(jwtUtil.generateToken("user1")).thenReturn("mock-jwt");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user1");
        loginRequest.setPassword(rawPassword);

        ResponseEntity<?> response = authController.login(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("token"));
    }

    @Test
    void testLoginFailureInvalidPassword() {
        Users mockUser = new Users();
        mockUser.setUsername("user1");
        mockUser.setPassword(passwordEncoder.encode("rightPassword"));

        when(userRepo.findByUsername("user1")).thenReturn(mockUser);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user1");
        loginRequest.setPassword("wrongPassword");

        ResponseEntity<?> response = authController.login(loginRequest);

        assertEquals(401, response.getStatusCodeValue());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("error"));
    }

    @Test
    void testRegisterSuccess() {
        Users user = new Users();
        user.setUsername("newuser");
        user.setPassword("password");

        Users savedUser = new Users();
        savedUser.setUsername("newuser");
        savedUser.setPassword("password");
        savedUser.setUserId(1); // Hier Integer statt long

        when(userService.existsByUsername("newuser")).thenReturn(false);
        when(userService.createUser(user)).thenReturn(savedUser);

        ResponseEntity<?> response = authController.register(user);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("message"));
    }

    @Test
    void testRegisterUsernameExists() {
        Users user = new Users();
        user.setUsername("existing");
        user.setPassword("password");

        when(userService.existsByUsername("existing")).thenReturn(true);

        ResponseEntity<?> response = authController.register(user);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("error"));
    }

    @Test
    void testRegisterFailsToSaveUser() {
        Users user = new Users();
        user.setUsername("failuser");
        user.setPassword("password");

        when(userService.existsByUsername("failuser")).thenReturn(false);
        when(userService.createUser(user)).thenReturn(new Users()); // kein userId gesetzt

        ResponseEntity<?> response = authController.register(user);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("error"));
    }
}
