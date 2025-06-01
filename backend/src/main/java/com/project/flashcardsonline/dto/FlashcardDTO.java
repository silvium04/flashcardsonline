package com.project.flashcardsonline.dto;

import java.time.LocalDateTime;

public class FlashcardDTO {
    private Integer flashcardId;
    private Integer deckId;
    private String frontText;
    private String backText;
	private Integer step;
    private LocalDateTime lastRight;

    public FlashcardDTO() {}

    public FlashcardDTO(Integer deckId, String frontText, String backText) {
        this.deckId = deckId;
        this.frontText = frontText;
        this.backText = backText;
    }

    public FlashcardDTO(Integer deckId, String frontText, String backText, Integer step, LocalDateTime lastRight) {
        this.deckId = deckId;
        this.frontText = frontText;
        this.backText = backText;
        this.step = step;
        this.lastRight = lastRight;
    }
    public FlashcardDTO(Integer flashcardId, Integer deckId, String frontText, String backText, Integer step, LocalDateTime lastRight) {
        this.flashcardId = flashcardId;
        this.deckId = deckId;
        this.frontText = frontText;
        this.backText = backText;
        this.step = step;
        this.lastRight = lastRight;
    }

    public FlashcardDTO(Integer flashcardId, Integer deckId, String frontText, String backText) {
        this.flashcardId = flashcardId;
        this.deckId = deckId;
        this.frontText = frontText;
        this.backText = backText;
    }

    public Integer getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Integer flashcardId) {
        this.flashcardId = flashcardId;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public LocalDateTime getLastRight() {
        return lastRight;
    }

    public void setLastRight(LocalDateTime lastRight) {
        this.lastRight = lastRight;
    }
}
