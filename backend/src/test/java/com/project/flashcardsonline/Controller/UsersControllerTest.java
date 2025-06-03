package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersControllerTest {

    private UserService userService;
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        usersController = new UsersController(userService);
    }

    @Test
    void testGetAllUsers() {
        Users user1 = new Users();
        user1.setUsername("alice");

        Users user2 = new Users();
        user2.setUsername("bob");

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        List<Users> result = usersController.getAllUsers(); // Direkt als Liste

        assertEquals(2, result.size());
        assertEquals("alice", result.get(0).getUsername());
    }
}
