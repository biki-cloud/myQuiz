package utils.quiz;


import org.junit.jupiter.api.Test;
import com.example.myquiz.quiz.Question;
import com.example.myquiz.quiz.HtmlParser;
import com.example.myquiz.quiz.Questioner;
import com.example.myquiz.utils.FileReader;

import java.util.ArrayList;

class QuizHtmlReaderTests {

    /**
     * htmlのbody部分の文字列を読み込み、問題部分を取得し、Questionクラスへ入れる。
     * 正確にQuestionクラスに代入できているかを試験する。
     */
    @Test
    void parseHtmlElement() {
        HtmlParser htmlParser = new HtmlParser();
        FileReader fr = new FileReader();

        ClassLoader classLoader = getClass().getClassLoader();

        String text = fr.read(classLoader.getResource("quizFiles/CS2205/UNIT1/1.html").getFile());
        ArrayList<Question> questions = htmlParser.getQuestionsFromHTMLText(text);

        if (questions.size() != 10) {
            throw new IllegalStateException("htmlの中から問題を１０個取得できていません。実際に取得した数: " + questions.size());
        }

        for (Question question : questions) {
            if (question.getQuestion().equals("")) {
                throw new IllegalStateException("問題が空です");
            }

            if (question.getChoices().size() == 0) {
                throw new IllegalStateException("選択肢が空です");
            }

            if (question.getAnswer().equals("")) {
                throw new IllegalStateException("答えが空です");
            }
        }
    }

}
