package com.project.flashcardsonline.services;

import com.project.flashcardsonline.repositories.DecksRepository;
import org.springframework.stereotype.Service;

@Service
public class DecksService {
	private final DecksRepository decksRepository;

	public DecksService(DecksRepository decksRepository) {
		this.decksRepository = decksRepository;
	}


}
