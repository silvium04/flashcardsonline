package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "FLASHCARDS")
public class Flashcards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flashcardId;
    @ManyToOne
    @JoinColumn(name="deckId",nullable = false)
    private Decks deck;
    @Column
    private String front;
    @Column
    private String back;
    @Column
    private Blob frontImage;
    @Column
    private Blob backImage;
    @Column
    private LocalDateTime creationDate;
    @Column
    private LocalDateTime lastRight;
    @Column
    private Integer step;

    public Integer getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Integer flashcardId) {
        this.flashcardId = flashcardId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Blob getBackImage() {
        return backImage;
    }

    public void setBackImage(Blob backImage) {
        this.backImage = backImage;
    }

    public LocalDateTime getLastRight() {
        return lastRight;
    }

    public void setLastRight(LocalDateTime lastRight) {
        this.lastRight = lastRight;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Blob getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Blob frontImage) {
        this.frontImage = frontImage;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public Decks getDeck() {
        return deck;
    }

    public void setDeck(Decks deck) {
        this.deck = deck;
    }
}
