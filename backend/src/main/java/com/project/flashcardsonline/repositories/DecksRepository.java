package com.project.flashcardsonline.repositories;

import com.project.flashcardsonline.model.Decks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecksRepository extends JpaRepository<Decks, Integer> {
	Decks getDecksByDeckId(Integer deckId);
}
