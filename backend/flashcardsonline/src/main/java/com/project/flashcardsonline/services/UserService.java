package com.project.flashcardsonline.services;

import com.project.flashcardsonline.model.Users;
import com.project.flashcardsonline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> usersList(){
        return userRepository.findAll();
    }

}
