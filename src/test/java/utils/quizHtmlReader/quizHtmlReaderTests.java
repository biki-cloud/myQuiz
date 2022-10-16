package utils.quizHtmlReader;


import org.junit.jupiter.api.Test;
import utils.fileReader.FileReader;

import java.io.File;

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
        quizHtmlReader.readHtmlText(text);
    }

}
