package com.project.flashcardsonline.service;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.repositories.DecksRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class for managing learning sessions.
 * This class handles the logic for learning flashcards and decks.
 */
@Service
public class LearningService {
	private final FlashcardsService flashcardsService;
	private final DecksRepository decksRepository;

	public LearningService(FlashcardsService flashcardsService, DecksRepository decksRepository) {
		this.flashcardsService = flashcardsService;
		this.decksRepository = decksRepository;
	}

	public List<Flashcards> getRandomOrderedFlashcards(Integer deckId){
		Decks deck = decksRepository.getDecksByDeckId(deckId);
		List<Flashcards> flashcards = deck.getIncludedFlashcards();
		Collections.shuffle(flashcards);
		return flashcards;
	}

	public List<Flashcards> getBackwardsFlashcards(Integer deckId){
		Decks deck = decksRepository.getDecksByDeckId(deckId);
		List<Flashcards> flashcards = deck.getIncludedFlashcards();
		Collections.reverse(flashcards);
		return flashcards;
	}

	public List<Flashcards> getNormalFlashcards(Integer deckId){
		Decks deck = decksRepository.getDecksByDeckId(deckId);
		return deck.getIncludedFlashcards();
	}

	public List<Flashcards> getLeitnerFlashcards(Integer deckId){
		Decks deck = decksRepository.getDecksByDeckId(deckId);
		List<Flashcards> flashcards = deck.getIncludedFlashcards();
		return sortLeitnerFlashcards(flashcards);
	}

	public List<Flashcards> sortLeitnerFlashcards(List<Flashcards> flashcards){
		List<Flashcards> flashcardsSorted = new ArrayList<>();
		for (Flashcards flashcard : flashcards) {
			if (isDue(flashcard)) {
				flashcardsSorted.add(flashcard);
			}
		}
		flashcardsSorted.sort((f1, f2) -> Integer.compare(f1.getStep(), f2.getStep()));
		return flashcardsSorted;
	}

	public boolean isDue(Flashcards flashcard) {
		if (flashcard.getStep() == 1){
			return LocalDateTime.now().isAfter(flashcard.getLastRight().plusDays(1));
		} else if (flashcard.getStep() == 2) {
			return LocalDateTime.now().isAfter(flashcard.getLastRight().plusDays(2));
		} else if (flashcard.getStep() == 3) {
			return LocalDateTime.now().isAfter(flashcard.getLastRight().plusDays(4));
		} else if (flashcard.getStep() == 4) {
			return LocalDateTime.now().isAfter(flashcard.getLastRight().plusDays(8));
		} else if (flashcard.getStep() == 5) {
			return LocalDateTime.now().isAfter(flashcard.getLastRight().plusDays(16));
		}
		return false;
	}

	public void updateLeitnerFlashcard(Flashcards flashcard) {
		System.out.println("Updating flashcard: " + flashcard.toString());
		Flashcards updateCard = flashcardsService.getById(flashcard.getFlashcardId());
		System.out.println("Flashcard before update: " + updateCard.toString());
		if (updateCard.getStep() < 5) {
			updateCard.setStep(updateCard.getStep() + 1);
			System.out.println("Step increased to: " + updateCard.getStep());
		}
		updateCard.setLastRight(LocalDateTime.now());
		flashcardsService.update(updateCard);
		System.out.println(flashcardsService.getById(flashcard.getFlashcardId()).toString());
	}
}
