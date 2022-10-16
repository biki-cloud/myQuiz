package utils.quiz;


import org.junit.jupiter.api.Test;
import quiz.QuestionContent;
import quiz.QuizHtmlReader;
import quiz.QuizQuestioner;
import utils.fileReader.FileReader;

import java.io.File;
import java.util.ArrayList;

class QuizHtmlReaderTests {

    @Test
    void parseHtmlElement() {
        QuizHtmlReader quizHtmlReader = new QuizHtmlReader();
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
        File file = new File(classLoader.getResource("source.html").getFile());

        String text = fr.read(file);
        ArrayList<QuestionContent> questionContents = quizHtmlReader.readHtmlText(text);
        QuizQuestioner quizQuestioner = new QuizQuestioner();

        for (QuestionContent questionContent1 : questionContents) {
            quizQuestioner.question(questionContent1);
        }
    }

}
