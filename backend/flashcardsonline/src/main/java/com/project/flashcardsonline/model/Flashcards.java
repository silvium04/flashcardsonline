package com.project.flashcardsonline.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "FLASHCARDS")
public class Flashcards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flashcardId;
    @ManyToOne
    @JoinColumn(name="",nullable = false)
    private Decks deck;
    @Column
    private String front;
    @Column
    private String back;
    @Column
    private Blob frontImage;
    @Column
    private Blob backImage;
    @Column
    private LocalDateTime creationDate;
    @Column
    private LocalDateTime lastRight;
    @Column
    private Integer step;
}
