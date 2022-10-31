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
                quizContents.add(getQuestionFromHTMLElement(element));
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
    public QuizContent getQuestionFromHTMLElement(Element element) {
        final Choices choices = new Choices(new ArrayList<>());
        QuizContent quizContent = new QuizContent();
        for (Element ele : element.getAllElements()) {
            final String className = ele.className();
            final String textOfElement = ele.text();
            switch (className) {
                case "qtext" -> {
                    final Question question = new Question(textOfElement);
                    quizContent.setQuestion(question);
                }
                case "flex-fill ml-1", "ml-1" -> choices.add(Choices.removeLastDotFromSentence(textOfElement));
                case "rightanswer" -> {
                    // exmaples of ele.ownText()
                    // The correct answer is: software architecture and a software design pattern.
                    // The correct answer is: 'True'
                    // The correct answer is: 'True'
                    Answer answer = Answer.getAnswerFromTextOfHtmlElement(textOfElement);
                    quizContent.setAnswer(answer);
                }
            }
        }
        System.out.println("-----------------------------");
        quizContent.setChoices(choices);
        quizContent.choicesContainsAnswer();
        System.out.println("quizContent: " + quizContent);
        return quizContent;
    }
}
