package com.dev.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.quizapp.doa.QuestionDoa;
import com.dev.quizapp.doa.QuizDoa;
import com.dev.quizapp.model.Question;
import com.dev.quizapp.model.QuestionWrapper;
import com.dev.quizapp.model.Quiz;

@Service
public class QuizService {
    
    @Autowired
    QuizDoa quizDoa;

    @Autowired
    QuestionDoa questionDoa;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDoa.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDoa.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDoa.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestion();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(), q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }



    
}
