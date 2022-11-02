package com.example.myquiz.quiz;


import org.jsoup.nodes.Element;

public class Answer {
    final public String value;

    public Answer(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("受け取った文字列が空です。");
        }
        this.value = value;
    }

    /**
     * 文字列の最後に.がついていた場合は削除し、文を返す。ついていない場合はそのままの文字列を返す。
     * @param sentence 対象も文字列
     * @return 加工後の文字列を返す
     */
    private static String removeLastDotFromSentence(final String sentence) {
         if (sentence.charAt(sentence.length() - 1) == '.') {
             return sentence.substring(0, sentence.length() - 1);
         }
         return sentence;
    }

    /**
     * 不必要な文字列を削除して返す
     * 受け取る文字列の例
     * 1: " software architecture and a software design pattern"
     * 2: "'True'" とか "'False'"
     * @param answer 加工目的の文字列
     * @return 不必要な文字列を削除した文字列を返す
     */
    private static String removeUnnecessaryChar(final String answer) {
        if (answer.contains("True") || answer.contains("False")) {
            return answer.substring(1, answer.length() - 1);
        } else {
            return answer.substring(1);
        }
    }

    /**
     * インスタンスの初期化を行うファクトリメソッド
     * html要素を受け取って、文字列処理し、Answerインスタンスを返す。
     * @param element html要素を受け取る
     * @return Answerインスタンスを返す。
     */
    public static Answer getAnswerFromHtmlElement(final Element element) {
        final String answerString = element.getElementsByClass("rightanswer").text();
        final String removedUnnecessaryString = answerString.substring(22); // 'True'
        final String dotRemovedAnswer = removeLastDotFromSentence(removedUnnecessaryString);
        final String wellProcessedAnswer = removeUnnecessaryChar(dotRemovedAnswer);
        return new Answer(wellProcessedAnswer);
    }
}
