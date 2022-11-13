package com.example.myquiz.quiz;

import org.jsoup.nodes.Element;

public class Question {
    final public String value;

    public Question(final String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("問題が空です");
        }
        this.value = value;
    }

    /**
     * html要素を受け取り、Questionオブジェクトを返すファクトリメソッド
     * @param element html要素を受け取る
     * @return Questionオブジェクトを返す
     */
    public static Question getQuestionFromHtmlElement(final Element element) {
        final String questionString = element.getElementsByClass("qtext").text();
        return new Question(questionString);
    }
}
