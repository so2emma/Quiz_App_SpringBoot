package com.dev.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.quizapp.doa.QuizDoa;

@Service
public class QuizService {
    
    @Autowired
    QuizDoa quizDoa;
}
