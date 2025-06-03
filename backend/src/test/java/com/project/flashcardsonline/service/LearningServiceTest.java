package com.project.flashcardsonline.service;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.repositories.DecksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LearningServiceTest {
	private FlashcardsService flashcardsService;
	private DecksRepository decksRepository;
	private LearningService learningService;

	private Flashcards card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
	private List<Flashcards> exampleFlashcards;

	@BeforeEach
	void setUp() {
		flashcardsService = mock(FlashcardsService.class);
		decksRepository = mock(DecksRepository.class);
		learningService = new LearningService(flashcardsService, decksRepository);

		card1 = new Flashcards(null, "Frage 1", "Antwort 1", LocalDateTime.now());
		card1.setStep(1);
		card1.setLastRight(LocalDateTime.now().minusHours(23));
		card2 = new Flashcards(null, "Frage 2", "Antwort 2", LocalDateTime.now());
		card2.setStep(1);
		card2.setLastRight(LocalDateTime.now().minusDays(1));
		card3 = new Flashcards(null, "Frage 3", "Antwort 3", LocalDateTime.now());
		card3.setStep(2);
		card3.setLastRight(LocalDateTime.now().minusHours(43));
		card4 = new Flashcards(null, "Frage 4", "Antwort 4", LocalDateTime.now());
		card4.setStep(2);
		card4.setLastRight(LocalDateTime.now().minusDays(2));
		card5 = new Flashcards(null, "Frage 5", "Antwort 5", LocalDateTime.now());
		card5.setStep(3);
		card5.setLastRight(LocalDateTime.now().minusHours(95));
		card6 = new Flashcards(null, "Frage 6", "Antwort 6", LocalDateTime.now());
		card6.setStep(3);
		card6.setLastRight(LocalDateTime.now().minusDays(4));
		card7 = new Flashcards(null, "Frage 7", "Antwort 7", LocalDateTime.now());
		card7.setStep(4);
		card7.setLastRight(LocalDateTime.now().minusHours(191));
		card8 = new Flashcards(null, "Frage 8", "Antwort 8", LocalDateTime.now());
		card8.setStep(4);
		card8.setLastRight(LocalDateTime.now().minusDays(8));
		card9 = new Flashcards(null, "Frage 9", "Antwort 9", LocalDateTime.now());
		card9.setStep(5);
		card9.setLastRight(LocalDateTime.now().minusHours(383));
		card10 = new Flashcards(null, "Frage 10", "Antwort 10", LocalDateTime.now());
		card10.setStep(5);
		card10.setLastRight(LocalDateTime.now().minusDays(16));

		exampleFlashcards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10);
	}

	@Test
	void testGetNormalDeck() {
		Decks deck = mock(Decks.class);
		when(deck.getIncludedFlashcards()).thenReturn(exampleFlashcards);
		when(decksRepository.getDecksByDeckId(1)).thenReturn(deck);

		List<Flashcards> result = learningService.getNormalFlashcards(1);

		assertIterableEquals(exampleFlashcards, result);
	}

	@Test
	void testGetBackwardsDeck() {
		Decks deck = mock(Decks.class);
		when(deck.getIncludedFlashcards()).thenReturn(exampleFlashcards);
		when(decksRepository.getDecksByDeckId(1)).thenReturn(deck);

		List<Flashcards> result = learningService.getBackwardsFlashcards(1);

		List<Flashcards> expected = Arrays.asList(card10, card9, card8, card7, card6, card5, card4, card3, card2, card1);
		assertIterableEquals(expected, result);
	}

	@Test
	void testGetRandomOrderedDeck() {
		Decks deck = mock(Decks.class);
		when(deck.getIncludedFlashcards()).thenReturn(exampleFlashcards);
		when(decksRepository.getDecksByDeckId(1)).thenReturn(deck);

		List<Flashcards> result = learningService.getRandomOrderedFlashcards(1);

		assertEquals(10, result.size());
		assertTrue(result.containsAll(exampleFlashcards));
	}

	@Test
	void testGetLeitnerDeck() {
		Decks deck = mock(Decks.class);
		when(deck.getIncludedFlashcards()).thenReturn(exampleFlashcards);
		when(decksRepository.getDecksByDeckId(1)).thenReturn(deck);

		List<Flashcards> result = learningService.getLeitnerFlashcards(1);


		assertEquals(5, result.size());
		assertFalse(result.contains(card1));
		assertTrue(result.contains(card2));
		assertFalse(result.contains(card3));
		assertTrue(result.contains(card4));
		assertFalse(result.contains(card5));
		assertTrue(result.contains(card6));
		assertFalse(result.contains(card7));
		assertTrue(result.contains(card8));
		assertFalse(result.contains(card9));
		assertTrue(result.contains(card10));
	}
}
