package utils.quizHtmlReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class QuizHtmlReader {
    public QuizHtmlReader() {
    }

    public void readHtmlText(String htmlText) {
        Document document = Jsoup.parse(htmlText);
        Elements allElements = document.getAllElements();

        ArrayList<QuestionContent> questionContents = new ArrayList<>();
        for (Element element : allElements) {
            // question-から始まるクラスの要素がクイズ１問のhtml要素になる
            if (element.id().contains("question-")) {
                questionContents.add(parseQuestionHTMLElement(element));
            }
        }

        for (QuestionContent questionContent : questionContents) {
            System.out.println(questionContent);
        }
    }

    public QuestionContent parseQuestionHTMLElement(Element element) {
        System.out.println("-----");
        QuestionContent questionContent = new QuestionContent();
        for (Element ele : element.getAllElements()) {
            if (ele.className().equals("qtext")) {
                questionContent.setQuestion(ele.ownText());
            }
            if (ele.className().equals("flex-fill ml-1") || ele.className().equals("ml-1")) {
                questionContent.addChoices(ele.ownText());
            }
            if (ele.className().equals("rightanswer")) {
                // exmaples of ele.ownText()
                // The correct answer is: software architecture and a software design pattern.
                // The correct answer is: 'True'
                String answerString = ele.ownText(); // The correct answer is: 'True'
                String distinctAnswer = answerString.substring(22, answerString.length() - 1); // 'True'
                if (answerString.contains("True") || answerString.contains("False")) {
                    distinctAnswer = distinctAnswer.substring(1, distinctAnswer.length() - 1);
                } else {
                    distinctAnswer = distinctAnswer.substring(1);
                }
                questionContent.setAnswer(distinctAnswer);
            }
        }
        return questionContent;
    }
}
