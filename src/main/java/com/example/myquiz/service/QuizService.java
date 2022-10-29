package com.example.myquiz.service;

import com.example.myquiz.model.Quiz;
import com.example.myquiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class QuizService {
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public String home(Model model, @ModelAttribute Quiz quiz) {
        System.out.println("homeメソッドが呼ばれました");
        model.addAttribute("questions", repository.findAll());
        return "home";
    }
}
