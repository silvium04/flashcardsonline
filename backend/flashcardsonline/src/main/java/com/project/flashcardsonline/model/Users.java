package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USERS")
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Decks> getDecks() {
        return decks;
    }

    public void setDecks(Set<Decks> decks) {
        this.decks = decks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
