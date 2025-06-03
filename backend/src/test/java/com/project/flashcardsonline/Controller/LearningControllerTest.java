package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.FlashcardDTO;
import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.service.LearningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LearningControllerTest {
	private LearningService learningService;
	private LearningController learningController;

	@BeforeEach
	void setUp() {
		learningService = mock(LearningService.class);
		learningController = new LearningController(learningService);
	}

	private Flashcards createMockFlashcard(int id, String front, String back, int step) {
		Flashcards card = new Flashcards();
		card.setFlashcardId(id);
		card.setFrontText(front);
		card.setBackText(back);
		card.setStep(step);
		card.setLastRight(LocalDateTime.now());

		Decks deck = new Decks();
		deck.setDeckId(42);
		card.setDeck(deck);

		return card;
	}

	@Test
	void getRandomOrderedDeck() {
		when(learningService.getRandomOrderedFlashcards(42)).thenReturn(
				List.of(createMockFlashcard(1, "Front1", "Back1", 1))
		);

		List<FlashcardDTO> result = learningController.getRandomOrderedDeck(42);

		assertEquals(1, result.size());
		assertEquals("Front1", result.get(0).getFrontText());
	}

	@Test
	void getBackwardsDeck() {
		when(learningService.getBackwardsFlashcards(42)).thenReturn(
				List.of(createMockFlashcard(2, "Front2", "Back2", 2))
		);

		List<FlashcardDTO> result = learningController.getBackwardsDeck(42);

		assertEquals(1, result.size());
		assertEquals("Front2", result.get(0).getFrontText());
	}

	@Test
	void getNormalDeck() {
		when(learningService.getNormalFlashcards(42)).thenReturn(
				List.of(createMockFlashcard(3, "Front3", "Back3", 3))
		);

		List<FlashcardDTO> result = learningController.getNormalDeck(42);

		assertEquals(1, result.size());
		assertEquals("Front3", result.get(0).getFrontText());
	}

	@Test
	void getLeitnerDeck() {
		when(learningService.getLeitnerFlashcards(42)).thenReturn(
				List.of(createMockFlashcard(4, "Front4", "Back4", 4))
		);

		List<FlashcardDTO> result = learningController.getLeitnerDeck(42);

		assertEquals(1, result.size());
		assertEquals("Front4", result.get(0).getFrontText());
	}

	@Test
	void updateLeitnerFlashcard() {
		Flashcards card = createMockFlashcard(5, "Front5", "Back5", 5);

		learningController.updateLeitnerFlashcard(card);

		verify(learningService, times(1)).updateLeitnerFlashcard(card);
	}
}
