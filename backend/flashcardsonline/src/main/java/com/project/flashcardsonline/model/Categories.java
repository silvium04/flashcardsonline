package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String name;
    @ManyToMany
    Set<Decks> decks;
}
