package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.DeckDTO;
import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.UserRepository;
import com.project.flashcardsonline.services.DecksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecksControllerTest {

    private DecksService decksService;
    private UserRepository userRepository;
    private DecksController decksController;

    @BeforeEach
    void setUp() {
        decksService = mock(DecksService.class);
        userRepository = mock(UserRepository.class);
        decksController = new DecksController(decksService, userRepository);
    }

    @Test
    void testGetAllDecksForUser() {
        // Mock Authentication and SecurityContext
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("testuser");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);

        try (MockedStatic<SecurityContextHolder> mockedStatic = mockStatic(SecurityContextHolder.class)) {
            mockedStatic.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            Users mockUser = new Users();
            mockUser.setUsername("testuser");

            Decks deck1 = new Decks();
            deck1.setDeckId(1);
            deck1.setName("Deck A");

            Decks deck2 = new Decks();
            deck2.setDeckId(2);
            deck2.setName("Deck B");

            when(userRepository.findByUsername("testuser")).thenReturn(mockUser);
            when(decksService.getDecksByUser(mockUser)).thenReturn(List.of(deck1, deck2));

            ResponseEntity<List<DeckDTO>> response = decksController.getAllDecksForUser();

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(2, response.getBody().size());
            assertEquals("Deck A", response.getBody().get(0).getName());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testCreateDeckSuccess() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser");

        Users mockUser = new Users();
        mockUser.setUsername("testuser");

        Decks savedDeck = new Decks();
        savedDeck.setDeckId(1);
        savedDeck.setName("New Deck");

        when(userRepository.findByUsername("testuser")).thenReturn(mockUser);
        when(decksService.saveDeck(any(Decks.class))).thenReturn(savedDeck);

        ResponseEntity<DeckDTO> response = decksController.createDeck(Map.of("name", "New Deck"), principal);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("New Deck", response.getBody().getName());
    }

    @Test
    void testCreateDeckFailure() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testuser");

        when(userRepository.findByUsername("testuser")).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<DeckDTO> response = decksController.createDeck(Map.of("name", "New Deck"), principal);

        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
