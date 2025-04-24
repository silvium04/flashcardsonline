package com.project.flashcardsonline.repositories;

import com.project.flashcardsonline.model.Decks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecksRepository extends JpaRepository<Decks, Integer> {
}
