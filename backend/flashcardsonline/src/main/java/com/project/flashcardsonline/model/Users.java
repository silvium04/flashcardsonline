package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String username;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<Decks> decks;
}
