package quiz;

import utils.fileReader.FileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Questioner {
    public static ArrayList<String> readFileInResourcesDir() throws IOException {
        ArrayList<String> allHtmlTexts = new ArrayList<>();
        FileReader fileReader = new FileReader();
        // resourcesディレクトリの中を探す
        File unitDirs = new File("src/main/resources/quizFiles/CS2205");
        for (File unitDir : unitDirs.listFiles()) {
            for (File htmlFile : unitDir.listFiles()) {
                allHtmlTexts.add(fileReader.read(htmlFile.getAbsolutePath()));
            }
        }
        return allHtmlTexts;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {

        HtmlParser quizHtmlReader = new HtmlParser();

        ArrayList<String> allHtmlTexts;

        // resourcesディレクトリにある全てのhtmlファイルの文字列を読み込み、配列で取得する
        allHtmlTexts = readFileInResourcesDir();

        // html要素がうまく取得できない時にファイル単体で試してみる時に使用する
        //  allHtmlTexts.add(readHtmlFile("src/main/resources/quizFiles/CS2205/UNIT1/2.html"));

        // 問題をシャッフルする
        Collections.shuffle(allHtmlTexts);

        Questioner questioner = new Questioner();

        for (String htmlText : allHtmlTexts) {
            ArrayList<Question> questions = quizHtmlReader.getQuestionsFromHTMLText(htmlText);

            for (Question question : questions) {
                questioner.questions(question);
            }

        }


    }

    /**
     * QuestionContentを受け取り、問題を出す。
     * 正解も判定する。
     * @param question 問題、選択肢、回答が入っている問題クラス
     * @return 正解したらtrue, 不正解ならfalseを返す
     */
    public boolean questions(Question question) {
        System.out.println("-------------------------------------------------");
        System.out.println("Question!!");
        System.out.println(question.getQuestion());
        System.out.println("");
        System.out.println("Choices");
        ArrayList<String> choices = question.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + ". " + choices.get(i));
        }
        System.out.println("");
        System.out.print("Your Answer: ");
        Scanner userInput = new Scanner(System.in);
        int userAnswer = userInput.nextInt();
//        System.out.println("userAnswer   : " + choices.get(userAnswer - 1));
        System.out.print("Result     : ");
        if (choices.get(userAnswer - 1).equals(question.getAnswer())) {
            System.out.println(ANSI_GREEN + "Correct!!!" + ANSI_RESET);
            return true;
        } else {
            System.out.println(ANSI_RED + "Wrong!!!" + ANSI_RESET);
            System.out.println("Correct    : " + question.getAnswer());
            return false;
        }
    }
}
