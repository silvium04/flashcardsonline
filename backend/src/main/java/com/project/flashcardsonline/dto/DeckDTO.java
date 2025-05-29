package com.project.flashcardsonline.dto;public class DeckDTO {
	private Integer deckId;
	private String name;

	public DeckDTO(Integer deckId, String name) {
		this.deckId = deckId;
		this.name = name;
	}

	public Integer getDeckId() {
		return deckId;
	}

	public void setDeckId(Integer deckId) {
		this.deckId = deckId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
