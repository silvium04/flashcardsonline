package com.project.flashcardsonline.Controller;

import com.project.flashcardsonline.dto.FlashcardDTO;
import com.project.flashcardsonline.model.Flashcards;
import com.project.flashcardsonline.services.LearningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/learning")
public class LearningController {
	public LearningService learningService;

	public LearningController(LearningService learningService) {
		this.learningService = learningService;
	}

	public List<FlashcardDTO> mapToDto(List<Flashcards> cards) {
		return cards.stream()
				.map(card -> new FlashcardDTO(
						card.getFlashcardId(),
						card.getDeck().getDeckId(),
						card.getFrontText(),
						card.getBackText(),
						card.getStep(),
						card.getLastRight()
				))
				.toList();
	}

	@GetMapping("/random/{deckId}")
	public List<FlashcardDTO> getRandomOrderedDeck(@PathVariable Integer deckId) {
		List<Flashcards> flashcards = learningService.getRandomOrderedFlashcards(deckId);
		return mapToDto(flashcards);
	}

	@GetMapping("/backwards/{deckId}")
	public List<FlashcardDTO> getBackwardsDeck(@PathVariable Integer deckId) {
		List<Flashcards> flashcards = learningService.getBackwardsFlashcards(deckId);
		return mapToDto(flashcards);
	}

	@GetMapping("/normal/{deckId}")
	public List<FlashcardDTO> getNormalDeck(@PathVariable Integer deckId) {
		List<Flashcards> flashcards = learningService.getNormalFlashcards(deckId);
		return mapToDto(flashcards);
	}

	@GetMapping("/leitner/{deckId}")
	public List<FlashcardDTO> getLeitnerDeck(@PathVariable Integer deckId) {
		List<Flashcards> flashcards = learningService.getLeitnerFlashcards(deckId);
		return mapToDto(flashcards);
	}

	@PutMapping("/updateLeitnerFlashcard")
	public void updateLeitnerFlashcard(@RequestBody Flashcards card) {
		learningService.updateLeitnerFlashcard(card);
	}
}
