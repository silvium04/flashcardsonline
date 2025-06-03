package com.project.flashcardsonline.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.flashcardsonline.dto.DeckDTO;
import com.project.flashcardsonline.model.Decks;
import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.service.DecksService;
import com.project.flashcardsonline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    private DeckDTO mapToDto(Decks deck) {
        return new DeckDTO(deck.getDeckId(), deck.getName());
    }

    @GetMapping("/getAllDecksForUser")
    public ResponseEntity<List<DeckDTO>> getAllDecksForUser() throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userRepository.findByUsername(username);
        List<Decks> decks = decksService.getDecksByUser(user);
        List<DeckDTO> dtos = decks.stream().map(this::mapToDto).toList();
        System.out.println(new ObjectMapper().writeValueAsString(dtos));
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/createDeck")
    public ResponseEntity<DeckDTO> createDeck(@RequestBody Map<String, String> body, Principal principal) {
        try {
            String deckName = body.get("name");
            Decks newDeck = new Decks(deckName, LocalDateTime.now(), userRepository.findByUsername(principal.getName()));
            Decks deck = decksService.saveDeck(newDeck);
            DeckDTO dto = mapToDto(deck);
            System.out.println(new ObjectMapper().writeValueAsString(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
