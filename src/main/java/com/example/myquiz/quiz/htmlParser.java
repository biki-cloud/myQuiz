package com.example.myquiz.quiz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class htmlParser {
    public htmlParser() {
    }

    /**
     * htmlのテキストを受け取り、中に存在する問題を全てQuestionクラスに格納し、配列で返す。
     * @param htmlText htmlの文字列
     * @return htmlの問題を全て格納したQuestionクラスの配列を返す。
     */
    public ArrayList<QuizContent> getQuestionsFromHTMLText(String htmlText) {
        Document document = Jsoup.parse(htmlText);
        Elements allElements = document.getAllElements();

        ArrayList<QuizContent> quizContents = new ArrayList<>();
        for (Element element : allElements) {
            // question-から始まるクラスの要素がクイズ１問のhtml要素になる
            if (element.id().contains("question-")) {
                quizContents.add(getQuizContentFromHTMLElement(element));
            }
        }

        return quizContents;
    }



    /**
     * 1つの問題が格納されているHTML要素を取得し、要素の中の問題、選択肢、答えを抽出。
     * その後、QuestionContentクラスへ格納し、QuestionContentオブジェクトを返す。
     * @param element 1つの問題が格納されているHTML要素
     * @return 問題、選択肢、答えが格納されたQuestionContentオブジェクト
     */
    public QuizContent getQuizContentFromHTMLElement(Element element) {
        final Question question = Question.getQuestionFromHtmlElement(element);
        final Answer answer = Answer.getAnswerFromHtmlElement(element);
        final Choices choices = Choices.getChoicesFromHtmlElement(element);
        final QuizContent quizContent = new QuizContent(question, choices, answer);

        System.out.println("-----------------------------");
        System.out.println("quizContent: " + quizContent);
        return quizContent;
    }
}
