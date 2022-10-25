package utils.quiz;


import org.junit.jupiter.api.Test;
import quiz.Question;
import quiz.HtmlParser;
import quiz.Questioner;
import utils.fileReader.FileReader;

import java.util.ArrayList;

class QuizHtmlReaderTests {

    @Test
    void parseHtmlElement() {
        HtmlParser quizHtmlReader = new HtmlParser();
        String htmlText = "<!DOCTYPE html>" +
                "    <html>" +
                "    <head>" +
                "       <title>Java Magazine</title>" +
                "    </head>" +
                "    <body>" +
                "       <h1>Hello World!</h1>" +
                "    </body>" +
                "</html>";
        FileReader fr = new FileReader();

        ClassLoader classLoader = getClass().getClassLoader();

        String text = fr.read(classLoader.getResource("quizFiles/CS2205/UNIT1/1.html").getFile());
        ArrayList<Question> questions = quizHtmlReader.getQuestionsFromHTMLText(text);
        Questioner questioner = new Questioner();

        for (Question question1 : questions) {
            questioner.questions(question1);
        }
    }

}
