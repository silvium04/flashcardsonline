package com.project.flashcardsonline.model;

import com.project.flashcardsonline.repositories.FlashcardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//REST-CONTROLLER NOCH EINBAUEN
@Service
public class FlashcardsService {

    @Autowired
    private FlashcardsRepository repository;

    public Flashcards create(Flashcards card) {
        return repository.save(card);
    }

    public Flashcards update(Flashcards card) {
        return repository.save(card);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Flashcards> getAll() {
        return repository.findAll();
    }

    public Flashcards getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
