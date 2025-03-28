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

}
