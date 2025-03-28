package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "DECKS")
public class Decks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deckId;
    @Column
    private String name;
    @Column
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "deck")
    private Set<Flashcards> includedFlashcards;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Flashcards> getIncludedFlashcards() {
        return includedFlashcards;
    }

    public void setIncludedFlashcards(Set<Flashcards> includedFlashcards) {
        this.includedFlashcards = includedFlashcards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
