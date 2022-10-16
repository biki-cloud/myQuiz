package quiz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class QuizHtmlReader {
    public QuizHtmlReader() {
    }

    public ArrayList<QuestionContent> readHtmlText(String htmlText) {
        Document document = Jsoup.parse(htmlText);
        Elements allElements = document.getAllElements();

        ArrayList<QuestionContent> questionContents = new ArrayList<>();
        for (Element element : allElements) {
            // question-から始まるクラスの要素がクイズ１問のhtml要素になる
            if (element.id().contains("question-")) {
                questionContents.add(parseQuestionHTMLElement(element));
            }
        }

        return questionContents;
    }

    public QuestionContent parseQuestionHTMLElement(Element element) {
        QuestionContent questionContent = new QuestionContent();
        for (Element ele : element.getAllElements()) {
            if (ele.className().equals("qtext")) {
                questionContent.setQuestion(ele.ownText());
            }
            if (ele.className().equals("flex-fill ml-1") || ele.className().equals("ml-1")) {
                String choice = ele.ownText();
                if (choice.charAt(choice.length() - 1) == '.') {
                    choice = choice.substring(0, choice.length() - 1);
                }
                questionContent.addChoices(choice);
            }
            if (ele.className().equals("rightanswer")) {
                // exmaples of ele.ownText()
                // The correct answer is: software architecture and a software design pattern.
                // The correct answer is: 'True'
                String answerString = ele.ownText(); // The correct answer is: 'True'
                String distinctAnswer = answerString.substring(22); // 'True'
                // 最後に.がついていた場合は削除する
                if (distinctAnswer.charAt(distinctAnswer.length() - 1) == '.') {
                    distinctAnswer = distinctAnswer.substring(0, distinctAnswer.length() - 1);
                }
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
