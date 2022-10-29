package com.example.myquiz.quiz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class HtmlParser {
    public HtmlParser() {
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
//        System.out.println("*******************");
        final Choices choices = new Choices(new ArrayList<String>());
        QuizContent quizContent = new QuizContent();
        for (Element ele : element.getAllElements()) {
//            System.out.println("-------------------------------------");
            final String className = ele.className();
            final String textOfElement = ele.text();
//            System.out.println("className      : " + className);
//            System.out.println("text of element: " + textOfElement);
            switch (className) {
                case "qtext":
                    final String textOfQuestion = textOfElement;
                    final Question question = new Question(textOfQuestion);
                    quizContent.setQuestion(question);
//                    question.setQuestion(textOfQuestion);
                    break;
                case "flex-fill ml-1":
                case "ml-1":
                    final String rawChoiceText = textOfElement;
                    if (rawChoiceText.charAt(rawChoiceText.length() - 1) == '.') {
                        final String dotRemovedChoiceText = rawChoiceText.substring(0, rawChoiceText.length() - 1);
//                        question.addChoices(dotRemovedChoiceText);
                        choices.add(dotRemovedChoiceText);
                    } else {
//                        question.addChoices(rawChoiceText);
                        choices.add(rawChoiceText);
                    }
                    break;
                case "rightanswer":
                    // exmaples of ele.ownText()
                    // The correct answer is: software architecture and a software design pattern.
                    // The correct answer is: 'True'
                    final String textOfAnswer = textOfElement; // The correct answer is: 'True'
                    String distinctAnswer = textOfAnswer.substring(22); // 'True'
                    // 最後に.がついていた場合は削除する
                    if (distinctAnswer.charAt(distinctAnswer.length() - 1) == '.') {
                        distinctAnswer = distinctAnswer.substring(0, distinctAnswer.length() - 1);
                    }
                    if (textOfAnswer.contains("True") || textOfAnswer.contains("False")) {
                        distinctAnswer = distinctAnswer.substring(1, distinctAnswer.length() - 1);
                    } else {
                        distinctAnswer = distinctAnswer.substring(1);
                    }
//                    question.setAnswer(distinctAnswer);
                    final Answer answer = new Answer(distinctAnswer);
                    quizContent.setAnswer(answer);
                    break;
//                default:
//                    System.out.println("------- "+ textOfElement);
            }
        }
        System.out.println("-----------------------------");
//        System.out.println("question: " + quizContent.getQuestion().value);
        quizContent.setChoices(choices);
        System.out.println("quizContent: " + quizContent.toString());
        return quizContent;
    }
}
