package com.example.myquiz.controller;

import com.example.myquiz.model.Quiz;
import com.example.myquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService service) {
        this.quizService = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        return quizService.home(model);
    }

    @PostMapping("/register")
    public String add(@Validated @ModelAttribute Quiz quiz,
                      BindingResult result,
                      Model model) {
        return quizService.register(quiz, result, model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return quizService.delete(id);
    }

    @GetMapping("/question")
    public String question(Model model) {
        return quizService.question(model);
    }

    @GetMapping("/view")
    public String view(Model model) {
        return quizService.view(model);
    }

    @PostMapping("/result")
    public String result(@ModelAttribute Quiz quiz, Model model) {
        return quizService.result(quiz, model);
    }
}


