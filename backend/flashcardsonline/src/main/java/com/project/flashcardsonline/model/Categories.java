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

    public Set<Decks> getDecks() {
        return decks;
    }

    public void setDecks(Set<Decks> decks) {
        this.decks = decks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
