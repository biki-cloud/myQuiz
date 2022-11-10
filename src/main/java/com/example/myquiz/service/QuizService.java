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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

@Service
public class QuizService {
    private final QuizRepository repository;

    private static final String messagesResourceName = "messages/messages";
    private static final ResourceBundle messagesResource = ResourceBundle.getBundle(messagesResourceName);

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public String home(Model model, @ModelAttribute Quiz quiz) {
        System.out.println("homeメソッドが呼ばれました");
        System.out.println(messagesResource.getString("welcome"));
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

        for (QuizContent quizContent : quizContents) {
            final Quiz quiz1 = new Quiz();
            quiz1.setQuestion(quizContent.getQuestion().value);
            StringBuilder concatenatedChoices = new StringBuilder();
            for (String choice : quizContent.getChoices().values) {
                concatenatedChoices.append(choice).append("---");
            }
            System.out.println(concatenatedChoices);
            quiz1.setChoices(concatenatedChoices.toString());
            quiz1.setAnswer(quizContent.getAnswer().value);
            quiz1.setHtmlString("<br />");
            repository.save(quiz1); // オブジェクトをDBに保存するJPAにあるメソッド
            System.out.println("-------------------------------");
        }

        /**
         redirectした場合は、下でreturnした後はControllerのGetMapping("/")を担当している
         home関数が呼び出される。
         */
        return "redirect:/";
    }

     /**
     * IDを受け取り、DBから削除するメソッド
     * @param id 削除したいレコードのID
     * @return home.html(ホームページ)を返す
     */
    public String delete(@PathVariable Long id) {
        System.out.println("deleteメソッドが呼ばれました");
        repository.deleteById(id); // IDからレコードを削除するJpaクラスのメソッド
        return "redirect:/view";
    }

    public Quiz getRandomQuiz(List<Quiz> quizList) {
        Random random = new Random();
        int randomNum = random.nextInt(quizList.size());
        return quizList.get(randomNum);
    }

    public String question(Model model) {
        List<Quiz> quizList = repository.findAll();
        model.addAttribute("targetQuestion", getRandomQuiz(quizList));
        return "question";
    }

    public String view(Model model) {
        model.addAttribute("questions", repository.findAll());
        return "view";
    }
}
