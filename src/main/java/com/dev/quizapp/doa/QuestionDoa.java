package com.dev.quizapp.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.quizapp.Question;

@Repository
public interface QuestionDoa extends JpaRepository<Question, Integer> {

    
}
