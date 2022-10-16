package quiz;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import utils.fileReader.FileReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizQuestioner {
    public static String readFileInResourcesDir(String filename) throws IOException {
        // resourcesディレクトリの中を探す
        Resource resource = new ClassPathResource(filename);
        InputStream inputStream = resource.getInputStream();
        String text = "";
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            text = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("error");
        }
        return text;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {

        QuizHtmlReader quizHtmlReader = new QuizHtmlReader();

        String text = readFileInResourcesDir("source.html");

        ArrayList<QuestionContent> questionContents = quizHtmlReader.readHtmlText(text);
        QuizQuestioner quizQuestioner = new QuizQuestioner();

        for (QuestionContent questionContent1 : questionContents) {
            quizQuestioner.question(questionContent1);
        }
    }

    public void question(QuestionContent questionContent) {
        System.out.println("-------------------------------------------------");
        System.out.println("Question!!");
        System.out.println(questionContent.getQuestion());
        System.out.println("");
        System.out.println("Choices");
        ArrayList<String> choices = questionContent.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + ". " + choices.get(i));
        }
        System.out.println("");
        System.out.print("Your Answer: ");
        Scanner userInput = new Scanner(System.in);
        int userAnswer = userInput.nextInt();
        System.out.println("****************************");
//        System.out.println("userAnswer   : " + choices.get(userAnswer - 1));
        System.out.print("Result: ");
        if (choices.get(userAnswer - 1).equals(questionContent.getAnswer())) {
            System.out.println(ANSI_GREEN + "Correct!!!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Wrong!!!" + ANSI_RESET);
            System.out.println("CorrectAnswer: " + questionContent.getAnswer());
        }
        System.out.println("****************************");

    }
}
