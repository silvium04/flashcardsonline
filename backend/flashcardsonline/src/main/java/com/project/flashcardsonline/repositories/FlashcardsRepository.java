package com.project.flashcardsonline.repositories;

import com.project.flashcardsonline.model.Flashcards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardsRepository extends JpaRepository<Flashcards, Integer> {
}
