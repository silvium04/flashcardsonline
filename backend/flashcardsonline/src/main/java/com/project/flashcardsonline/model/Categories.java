package model;

import jakarta.persistence.*;

import java.util.Set;

public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String name;
    @ManyToMany
    Set<Decks> decks;
}
