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

    // このファイルの中でメッセージリソースを使用するために定義している。
    private static final String messagesResourceName = "messages/messages";
    private static final ResourceBundle messagesResource = ResourceBundle.getBundle(messagesResourceName);

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    /**
     * 一覧表示、問題出す、クイズ登録へのリンクがあるホームページ。
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @return ホームページを返す。
     */
    public String home(Model model) {
        System.out.println("homeメソッドが呼ばれました");
        System.out.println(messagesResource.getString("welcome"));
        return "home";
    }

    /**
     * 問題を登録する用のページ
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @param quiz 問題を登録する用のQuizオブジェクト
     * @return 問題を登録する用のページを返す
     */
    public String registerPage(Model model, @ModelAttribute Quiz quiz) {
        model.addAttribute("quiz", quiz);
        return "registerPage";
    }

    /**
     * 問題を登録する。
     * 問題のhtml要素を受け取り、パースして実際の問題を１０問取り出し、DBへ登録する。
     * @param quiz 入力されたQuizオブジェクト.
     * @param result 正常にバインドされたか判断するオブジェクト
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @return バインドエラーが起きたら、登録せずにホームページを返す。
     *         エラーが起きなかったらホームページにリダイレクトする。
     */
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
            quiz1.setHtmlString("");
            repository.save(quiz1);
            System.out.println("-------------------------------");
        }

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

    /**
     * 問題をランダムに取り出す。
     * @param quizList ランダムに取り出すための問題のリスト
     * @return ランダムに取り出されたQuizオブジェクトを返す
     */
    public Quiz getRandomQuiz(List<Quiz> quizList) {
        Random random = new Random();
        int randomNum = random.nextInt(quizList.size());
        return quizList.get(randomNum);
    }

    /**
     * 問題を出し、提出してもらうページ
     * 問題はランダムに一問選択される。
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @return 問題を出し、提出してもらうページ
     */
    public String question(Model model) {
        List<Quiz> quizList = repository.findAll();
        final Quiz selectedQuiz = getRandomQuiz(quizList);
        model.addAttribute("question", selectedQuiz);
        return "question";
    }

    /**
     * DBに登録された問題一覧を表示
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @return DBに登録された問題一覧を表示するページを返す
     */
    public String view(Model model) {
        model.addAttribute("questions", repository.findAll());
        return "view";
    }

    /**
     * ユーザの答えの正解・不正解を判定し、ユーザに結果を表示するページを返す。
     * @param quiz 問題、選択肢、答え、ユーザの回答が入ったQuizオブジェクト
     * @param model 値をテンプレートに渡すためのオブジェクト
     * @return ユーザの結果を表示するページを返す。
     */
    public String result(@ModelAttribute Quiz quiz, Model model) {
        System.out.println("answerが呼ばれました");

        // ユーザの正解・不正解を判定
        String result;
        if (quiz.getUserAnswer().equals(quiz.getAnswer())) {
            result = "Correct!";
        } else {
            result = "Wrong!";
        }

        model.addAttribute("result", result);
        model.addAttribute("question", quiz);

        // ユーザの答えは答え合わせの時にだけ使用するので、DBに保存する必要はない。
        quiz.setUserAnswer("");

        repository.save(quiz);

        return "result";
    }
}
