package quiz;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizQuestioner {
    public static ArrayList<String> readFileInResourcesDir() throws IOException {
        ArrayList<String> allHtmlTexts = new ArrayList<>();
        // resourcesディレクトリの中を探す
        File unitDirs = new File("src/main/resources/quizFiles/CS2205");
        for (File unitDir : unitDirs.listFiles()) {
            for (File htmlFile : unitDir.listFiles()) {
                System.out.println(htmlFile.getName());
                System.out.println(htmlFile.getAbsolutePath());

                allHtmlTexts.add(readHtmlFile(htmlFile.getAbsolutePath()));
            }
        }

        return allHtmlTexts;
    }

    public static String readHtmlFile(String htmlPath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(htmlPath);
        String text = "";
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            text = new String(bdata, StandardCharsets.UTF_8);
            return text;
        } catch (IOException e) {
            System.out.println("error");
        }
        return "";
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {

        QuizHtmlReader quizHtmlReader = new QuizHtmlReader();

        ArrayList<String> allHtmlTexts;

        // resourcesディレクトリにある全てのhtmlファイルの文字列を読み込み、配列で取得する
        allHtmlTexts = readFileInResourcesDir();

        // html要素がうまく取得できない時にファイル単体で試してみる時に使用する
        //  allHtmlTexts.add(readHtmlFile("src/main/resources/quizFiles/CS2205/UNIT1/2.html"));

        // 問題をシャッフルする
        Collections.shuffle(allHtmlTexts);
        for (String htmlText : allHtmlTexts) {
            ArrayList<QuestionContent> questionContents = quizHtmlReader.getQuestionContentsFromHTMLText(htmlText);
            QuizQuestioner quizQuestioner = new QuizQuestioner();

            for (QuestionContent questionContent1 : questionContents) {
                quizQuestioner.question(questionContent1);
            }

        }


    }

    /**
     * QuestionContentを受け取り、問題を出す。
     * 正解も判定する。
     * @param questionContent 問題、選択肢、回答が入っている問題クラス
     */
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
//        System.out.println("userAnswer   : " + choices.get(userAnswer - 1));
        System.out.print("Result     : ");
        if (choices.get(userAnswer - 1).equals(questionContent.getAnswer())) {
            System.out.println(ANSI_GREEN + "Correct!!!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Wrong!!!" + ANSI_RESET);
            System.out.println("Correct    : " + questionContent.getAnswer());
        }

    }
}
