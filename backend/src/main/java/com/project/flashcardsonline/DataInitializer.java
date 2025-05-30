package com.project.flashcardsonline;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.DecksService;
import com.project.flashcardsonline.services.FlashcardsService;
import com.project.flashcardsonline.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class DataInitializer {

	private final UserService userService;
	private final DecksService decksService;
	private final FlashcardsService flashcardsService;

	public DataInitializer(UserService userService, DecksService decksService, FlashcardsService flashcardsService) {
		this.userService = userService;
		this.decksService = decksService;
		this.flashcardsService = flashcardsService;
	}

	@PostConstruct
	public void init() {
		Users users = new Users("password", "user", "user","user");
		userService.createUser(users);
		System.out.println(users.toString());

		Decks deck1 = new Decks("Test Deck", LocalDateTime.now(), users);
		decksService.saveDeck(deck1);

		Flashcards flashcard1 = new Flashcards(deck1, "Question 1", "Answer 1", LocalDateTime.now());
		flashcardsService.create(flashcard1);
		Flashcards flashcard2 = new Flashcards(deck1, "Question 2", "Answer 2", LocalDateTime.now());
		flashcardsService.create(flashcard2);
		Flashcards flashcard3 = new Flashcards(deck1, "Question 3", "Answer 3", LocalDateTime.now());
		flashcardsService.create(flashcard3);
		Flashcards flashcard4 = new Flashcards(deck1, "Question 4", "Answer 4", LocalDateTime.now());
		flashcardsService.create(flashcard4);

		Decks deck2 = new Decks("Deck 2", LocalDateTime.now(), users);
		decksService.saveDeck(deck2);

		Flashcards flashcard5 = new Flashcards(deck2, "Question 1", "Answer 1", LocalDateTime.now());
		flashcardsService.create(flashcard5);
		Flashcards flashcard6 = new Flashcards(deck2, "Question 2", "Answer 2", LocalDateTime.now());
		flashcardsService.create(flashcard6);
		Flashcards flashcard7 = new Flashcards(deck2, "Question 3", "Answer 3", LocalDateTime.now());
		flashcardsService.create(flashcard7);
		Flashcards flashcard8 = new Flashcards(deck2, "Question 4", "Answer 4", LocalDateTime.now());
		flashcardsService.create(flashcard8);



	}
}
