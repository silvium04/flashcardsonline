package com.project.flashcardsonline.service;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.DecksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecksService {

	private final DecksRepository decksRepository;

	public DecksService(DecksRepository decksRepository) {
		this.decksRepository = decksRepository;
	}

	public List<Decks> getDecksByUser(Users user) {
		return decksRepository.findByUser(user);
	}

	public Decks saveDeck(Decks deck) {
		return decksRepository.save(deck);
	}
}
