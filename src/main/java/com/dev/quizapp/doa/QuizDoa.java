package com.dev.quizapp.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.quizapp.model.Quiz;

public interface QuizDoa extends JpaRepository<Quiz, Integer>{
    
}
