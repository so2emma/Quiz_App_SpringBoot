package com.dev.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.quizapp.Question;
import com.dev.quizapp.doa.QuestionDoa;

@Service
public class QuestionService {

    @Autowired
    QuestionDoa questionDoa;

    public List<Question> getAllQuestions() {
        return questionDoa.findAll();
    }

    public List<Question> getQuestionsByCategory(String category){
        return questionDoa.findByCategory(category);
    }

    public String addQuestion(Question question) {
         questionDoa.save(question);
         return "Success";
    }
}
