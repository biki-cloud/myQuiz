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
     * 文字列の最後に.がついていた場合は削除し、文を返す。ついていない場合はそのままの文字列を返す。
     * @param sentence 対象も文字列
     * @return 加工後の文字列を返す
     */
    public String removeLastDotFromSentence(final String sentence) {
         if (sentence.charAt(sentence.length() - 1) == '.') {
             final String dotRemovedSentence = sentence.substring(0, sentence.length() - 1);
             return dotRemovedSentence;
         }
         return sentence;
    }

    /**
     * 不必要な文字列を削除して返す
     * 受け取る文字列の例
     * 1: " software architecture and a software design pattern"
     * 2: "'True'" とか "'False'"
     * @param answer
     * @return
     */
    public String removeUnnecessaryChar(final String answer) {
        if (answer.contains("True") || answer.contains("False")) {
            return answer.substring(1, answer.length() - 1);
        } else {
            return answer.substring(1);
        }
    }

    /**
     * 1つの問題が格納されているHTML要素を取得し、要素の中の問題、選択肢、答えを抽出。
     * その後、QuestionContentクラスへ格納し、QuestionContentオブジェクトを返す。
     * @param element 1つの問題が格納されているHTML要素
     * @return 問題、選択肢、答えが格納されたQuestionContentオブジェクト
     */
    public QuizContent getQuestionFromHTMLElement(Element element) {
        final Choices choices = new Choices(new ArrayList<String>());
        QuizContent quizContent = new QuizContent();
        for (Element ele : element.getAllElements()) {
            final String className = ele.className();
            final String textOfElement = ele.text();
            switch (className) {
                case "qtext":
                    final String textOfQuestion = textOfElement;
                    final Question question = new Question(textOfQuestion);
                    quizContent.setQuestion(question);
                    break;
                case "flex-fill ml-1":
                case "ml-1":
                    final String rawChoiceText = textOfElement;
                    choices.add(removeLastDotFromSentence(rawChoiceText));
                    break;
                case "rightanswer":
                    // exmaples of ele.ownText()
                    // The correct answer is: software architecture and a software design pattern.
                    // The correct answer is: 'True'
                    final String textOfAnswer = textOfElement; // The correct answer is: 'True'
                    final String removedUnnecessaryString = textOfAnswer.substring(22); // 'True'
                    final String dotRemovedAnswer = removeLastDotFromSentence(removedUnnecessaryString);
                    final String wellProcessedAnswer = removeUnnecessaryChar(dotRemovedAnswer);
                    quizContent.setAnswer(new Answer(wellProcessedAnswer));
                    break;
            }
        }
        System.out.println("-----------------------------");
        quizContent.setChoices(choices);
        quizContent.choicesContainsAnswer();
        System.out.println("quizContent: " + quizContent.toString());
        return quizContent;
    }
}
