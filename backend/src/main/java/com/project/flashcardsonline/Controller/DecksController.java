package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.services.DecksService;
import com.project.flashcardsonline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DecksController {

    private final DecksService decksService;
    private final UserRepository userRepository;

    @Autowired
    public DecksController(DecksService decksService, UserRepository userRepository) {
        this.decksService = decksService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Decks>> getAllDecksForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userRepository.findByUsername(username);
        List<Decks> decks = decksService.getDecksByUser(user);
        return ResponseEntity.ok(decks);
    }

    @PostMapping
    public ResponseEntity<Decks> createDeck(@RequestBody Decks deckRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userRepository.findByUsername(username);
        deckRequest.setUser(user);
        deckRequest.setCreationDate(LocalDateTime.now());
        Decks savedDeck = decksService.saveDeck(deckRequest);
        return ResponseEntity.ok(savedDeck);
    }
}
