package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

public class Decks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String name;
    @Column
    private LocalDateTime creationDate;
    @ManyToMany
    private Set<Flashcards> includedFlashcards;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

}
