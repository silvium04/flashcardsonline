package com.project.flashcardsonline.services;

import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.repositories.FlashcardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class FlashcardsService {

    private final FlashcardsRepository repository;

    public FlashcardsService(FlashcardsRepository repository) {
        this.repository = repository;
        }

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

    public List<Flashcards> getByCategoryId(Integer categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

}



