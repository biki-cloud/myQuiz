package com.example.myquiz.service;

import com.example.myquiz.model.Quiz;
import com.example.myquiz.quiz.QuizContent;
import com.example.myquiz.quiz.htmlParser;
import com.example.myquiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@Service
public class QuizService {
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public String home(Model model, @ModelAttribute Quiz quiz) {
        System.out.println("homeメソッドが呼ばれました");
        model.addAttribute("quizContents", repository.findAll());
        return "home";
    }

    public String register(@Validated @ModelAttribute Quiz quiz,
                      BindingResult result,
                      Model model) {
        System.out.println("registerメソッドが呼ばれました");
        model.addAttribute("quizContents", repository.findAll());
        if (result.hasErrors()) {
            System.out.println("バインドエラーが起きました");
            System.out.println(result.getFieldErrors()); // エラー内容を見る
            return "home";
        }

        final htmlParser htmlParser = new htmlParser();
        final ArrayList<QuizContent> quizContents = htmlParser.getQuestionsFromHTMLText(quiz.getHtmlString());
        final QuizContent quizContent = quizContents.get(0);
        quiz.setQuestion(quizContent.getQuestion().value);
        StringBuilder concatenatedChoices = new StringBuilder();
        for (String choice: quizContent.getChoices().values) {
            concatenatedChoices.append(choice).append("------");
        }
        quiz.setChoices(concatenatedChoices.toString());
        quiz.setAnswer(quizContent.getAnswer().value);
        quiz.setHtmlString("temp html string");
        repository.save(quiz); // オブジェクトをDBに保存するJPAにあるメソッド
        /**
        redirectした場合は、下でreturnした後はControllerのGetMapping("/")を担当している
        home関数が呼び出される。
         */
        return "redirect:/";
    }
}
