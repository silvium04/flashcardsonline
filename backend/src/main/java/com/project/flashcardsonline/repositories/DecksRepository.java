package com.project.flashcardsonline.repositories;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecksRepository extends JpaRepository<Decks, Integer> {
	List<Decks> findByUser(Users user);

	// 👉 Diese Methode hinzufügen
	Decks getDecksByDeckId(Integer deckId);
}
