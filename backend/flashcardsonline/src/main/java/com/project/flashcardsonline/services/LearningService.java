package com.project.flashcardsonline.services;

import org.springframework.stereotype.Service;

/**
 * Service class for managing learning sessions.
 * This class handles the logic for learning flashcards and decks.
 */
@Service
public class LearningService {
	private final FlashcardsService flashcardsService;
	private final DecksService decksService;

	public LearningService(FlashcardsService flashcardsService, DecksService decksService) {
		this.flashcardsService = flashcardsService;
		this.decksService = decksService;
	}



}
