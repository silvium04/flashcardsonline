package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.services.FlashcardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardsController {

    private final FlashcardsService service;

    public FlashcardsController(FlashcardsService service) {
        this.service = service;
    }

    @PostMapping
    public Flashcards createCard(@RequestBody Flashcards card) {
        return service.create(card);
    }

    @PutMapping
    public Flashcards updateCard(@RequestBody Flashcards card) {
        return service.update(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping
    public List<Flashcards> getAllCards() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Flashcards getCardById(@PathVariable Integer id) {
        return service.getById(id);
    }


    @GetMapping("/by-category/{categoryId}")
    public List<Flashcards> getByCategory(@PathVariable Integer categoryId) {
        return service.getByCategoryId(categoryId);
    }


}