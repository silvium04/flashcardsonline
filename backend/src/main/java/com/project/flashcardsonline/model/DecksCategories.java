package com.project.flashcardsonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DECKS_CATEGORIES")
public class DecksCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deckCategoryId;

    @ManyToOne
    @JoinColumn(name = "deckId", nullable = false)
    private Decks deck;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Categories category;

    public Integer getDeckCategoryId() {
        return deckCategoryId;
    }

    public void setDeckCategoryId(Integer deckCategoryId) {
        this.deckCategoryId = deckCategoryId;
    }

    public Decks getDeck() {
        return deck;
    }

    public void setDeck(Decks deck) {
        this.deck = deck;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
