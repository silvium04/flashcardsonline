package com.project.flashcardsonline.repositories;

import com.project.flashcardsonline.model.Flashcards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardsRepository extends JpaRepository<Flashcards, Integer> {


    @Query("SELECT f FROM Flashcards f WHERE f.deck.deckId IN " +
            "(SELECT dc.deck.deckId FROM DecksCategories dc WHERE dc.category.categoryId = :categoryId)")
    List<Flashcards> findAllByCategoryId(@Param("categoryId") Integer categoryId);


}
