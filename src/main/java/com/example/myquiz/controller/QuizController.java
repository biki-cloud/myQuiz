package com.example.myquiz.controller;

import com.example.myquiz.model.Quiz;
import com.example.myquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService service) {
        this.quizService = service;
    }

    @GetMapping("/")
    public String home(Model model, @ModelAttribute Quiz quiz) {
        return quizService.home(model, quiz);
    }
}


