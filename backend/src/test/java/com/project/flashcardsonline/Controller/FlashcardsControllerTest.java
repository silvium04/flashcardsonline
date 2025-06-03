package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.FlashcardDTO;
import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.service.FlashcardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlashcardsControllerTest {

    private FlashcardsService flashcardsService;
    private FlashcardsController controller;

    @BeforeEach
    void setUp() {
        flashcardsService = mock(FlashcardsService.class);
        controller = new FlashcardsController(flashcardsService);
    }

    @Test
    void testCreateCard() {
        Flashcards card = new Flashcards();
        card.setFrontText("Front");
        card.setBackText("Back");

        Flashcards created = new Flashcards();
        created.setFlashcardId(1);
        created.setFrontText("Front");
        created.setBackText("Back");
        created.setStep(1);
        created.setLastRight(LocalDateTime.now());

        when(flashcardsService.create(any())).thenReturn(created);

        Flashcards result = controller.createCard(card);

        assertEquals("Front", result.getFrontText());
        assertEquals(1, result.getStep());
        assertNotNull(result.getLastRight());
    }

    @Test
    void testUpdateCard() {
        Flashcards card = new Flashcards();
        card.setFlashcardId(1);
        card.setFrontText("Updated");

        when(flashcardsService.update(any())).thenReturn(card);

        Flashcards result = controller.updateCard(card);

        assertEquals("Updated", result.getFrontText());
    }

    @Test
    void testDeleteCard() {
        controller.deleteCard(1);
        verify(flashcardsService, times(1)).delete(1);
    }

    @Test
    void testGetAllCards() {
        Flashcards card1 = new Flashcards();
        card1.setFlashcardId(1);
        Flashcards card2 = new Flashcards();
        card2.setFlashcardId(2);

        when(flashcardsService.getAll()).thenReturn(List.of(card1, card2));

        List<Flashcards> result = controller.getAllCards();

        assertEquals(2, result.size());
    }

    @Test
    void testGetCardById() {
        Flashcards card = new Flashcards();
        card.setFlashcardId(1);

        when(flashcardsService.getById(1)).thenReturn(card);

        Flashcards result = controller.getCardById(1);

        assertEquals(1, result.getFlashcardId());
    }

    @Test
    void testGetByCategory() {
        Flashcards card = new Flashcards();
        card.setFlashcardId(1);

        when(flashcardsService.getByCategoryId(5)).thenReturn(List.of(card));

        List<Flashcards> result = controller.getByCategory(5);

        assertEquals(1, result.size());
    }

    @Test
    void testGetFlashcardDTOsByDeck() {
        Flashcards card = new Flashcards();
        card.setFlashcardId(1);
        card.setFrontText("Q?");
        card.setBackText("A");
        card.setStep(1);
        card.setLastRight(LocalDateTime.now());

        Decks deck = new Decks();
        deck.setDeckId(100);
        card.setDeck(deck);

        when(flashcardsService.getByDeckId(100)).thenReturn(List.of(card));

        List<FlashcardDTO> result = controller.getFlashcardDTOsByDeck(100);

        assertEquals(1, result.size());
        assertEquals("Q?", result.get(0).getFrontText());
        assertEquals("A", result.get(0).getBackText());
        assertEquals(100, result.get(0).getDeckId());
    }
}
