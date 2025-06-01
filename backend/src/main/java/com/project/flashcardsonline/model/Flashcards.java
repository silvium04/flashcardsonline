package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    private String frontText;
    @Column
    private String backText;
    @Column
    private byte[] frontImage;
    @Column
    private byte[] backImage;
    @Column
    private LocalDateTime creationDate;
    @Column
    private LocalDateTime lastRight;
    @Column
    private Integer step;

    public Flashcards(Decks deck, String frontText, String backText, LocalDateTime creationDate) {
        this.deck = deck;
        this.frontText = frontText;
        this.backText = backText;
        this.creationDate = creationDate;
    }

    public Flashcards(Decks deck, String frontText, String backText, LocalDateTime creationDate, LocalDateTime lastRight, Integer step) {
        this.deck = deck;
        this.frontText = frontText;
        this.backText = backText;
        this.creationDate = creationDate;
        this.lastRight = lastRight;
        this.step = step;
    }

    public Flashcards() {

    }

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

    public byte[] getBackImage() {
        return backImage;
    }

    public void setBackImage(byte[] backImage) {
        this.backImage = backImage;
    }

    public LocalDateTime getLastRight() {
        return lastRight;
    }

    public void setLastRight(LocalDateTime lastRight) {
        this.lastRight = lastRight;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String back) {
        this.backText = back;
    }

    public byte[] getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(byte[] frontImage) {
        this.frontImage = frontImage;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public Decks getDeck() {
        return deck;
    }

    public void setDeck(Decks deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return "Flashcards{" +
                "flashcardId=" + flashcardId +
                ", deck=" + deck +
                ", frontText='" + frontText + '\'' +
                ", backText='" + backText + '\'' +
                ", frontImage=" + Arrays.toString(frontImage) +
                ", backImage=" + Arrays.toString(backImage) +
                ", creationDate=" + creationDate +
                ", lastRight=" + lastRight +
                ", step=" + step +
                '}';
    }
}
