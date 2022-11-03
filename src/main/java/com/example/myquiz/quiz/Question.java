package com.example.myquiz.quiz;

import org.jsoup.nodes.Element;

public class Question {
    final public String value;

    public Question(String value) {
        validation(value);
        this.value = value;
    }

    private static void validation(final String text) {
        if (text.equals("")) {
            throw new IllegalArgumentException("問題が空です。");
        }
    }

    /**
     * html要素を受け取り、Questionオブジェクトを返すファクトリメソッド
     * @param element html要素を受け取る
     * @return Questionオブジェクトを返す
     */
    public static Question getQuestionFromHtmlElement(final Element element) {
        final String questionString = element.getElementsByClass("qtext").text();
        validation(questionString);
        return new Question(questionString);
    }
}
