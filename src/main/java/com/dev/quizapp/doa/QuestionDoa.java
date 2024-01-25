package com.dev.quizapp.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.quizapp.model.Question;

@Repository
public interface QuestionDoa extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);
    
}
