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

		Decks deck = new Decks("Test Deck", LocalDateTime.now(), users);
		decksService.saveDeck(deck);

		Flashcards flashcard1 = new Flashcards(deck, "Question 1", "Answer 1", LocalDateTime.now());
		flashcardsService.create(flashcard1);
		Flashcards flashcard2 = new Flashcards(deck, "Question 2", "Answer 2", LocalDateTime.now());
		flashcardsService.create(flashcard2);
		Flashcards flashcard3 = new Flashcards(deck, "Question 3", "Answer 3", LocalDateTime.now());
		flashcardsService.create(flashcard3);
		Flashcards flashcard4 = new Flashcards(deck, "Question 4", "Answer 4", LocalDateTime.now());
		flashcardsService.create(flashcard4);



	}
}
