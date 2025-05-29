package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
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
    @OrderBy("flashcardId ASC")
    private List<Flashcards> includedFlashcards;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    public Decks(String name, LocalDateTime creationDate, Users user) {
        this.name = name;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Decks() {

    }

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

    public List<Flashcards> getIncludedFlashcards() {
        return includedFlashcards;
    }

    public void setIncludedFlashcards(List<Flashcards> includedFlashcards) {
        this.includedFlashcards = includedFlashcards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "deck")
    private Set<DecksCategories> decksCategories;

}
