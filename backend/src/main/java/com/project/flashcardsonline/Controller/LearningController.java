package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.services.LearningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/learning")
public class LearningController {
	public LearningService learningService;

	public LearningController(LearningService learningService) {
		this.learningService = learningService;
	}

	@GetMapping("/random/{deckId}")
	public List<Flashcards> getRandomOrderedDeck(@PathVariable Integer deckId) {
		return learningService.getRandomOrderedDeck(deckId);
	}

	@GetMapping("/backwards/{deckId}")
	public List<Flashcards> getBackwardsDeck(@PathVariable Integer deckId) {
		return learningService.getBackwardsDeck(deckId);
	}

	@GetMapping("/normal/{deckId}")
	public List<Flashcards> getNormalDeck(@PathVariable Integer deckId) {
		return learningService.getNormalDeck(deckId);
	}

	@GetMapping("/leitner/{deckId}")
	public List<Flashcards> getLeitnerDeck(@PathVariable Integer deckId) {
		return learningService.getLeitnerDeck(deckId);
	}
}
