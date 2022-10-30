package utils.quiz;


import com.example.myquiz.quiz.QuizContent;
import org.junit.jupiter.api.Test;
import com.example.myquiz.quiz.htmParser;
import com.example.myquiz.utils.FileReader;

import java.util.ArrayList;

class QuizHtmlReaderTests {

    /**
     * htmlのbody部分の文字列を読み込み、問題部分を取得し、Questionクラスへ入れる。
     * 正確にQuestionクラスに代入できているかを試験する。
     */
    @Test
    void parseHtmlElement() {
        htmParser htmParser = new htmParser();
        FileReader fr = new FileReader();

        ClassLoader classLoader = getClass().getClassLoader();

        String text = fr.read(classLoader.getResource("quizFiles/CS2205/UNIT1/1.html").getFile());
        ArrayList<QuizContent> quizContents = htmParser.getQuestionsFromHTMLText(text);

        if (quizContents.size() != 10) {
            throw new IllegalStateException("htmlの中から問題を１０個取得できていません。実際に取得した数: " + quizContents.size());
        }

        for (QuizContent quizContent : quizContents) {
            if (quizContent.getQuestion().equals("")) {
                throw new IllegalStateException("問題が空です");
            }

            if (quizContent.getChoices().values.size() == 0) {
                throw new IllegalStateException("選択肢が空です");
            }

            if (quizContent.getAnswer().equals("")) {
                throw new IllegalStateException("答えが空です");
            }
        }
    }

}
