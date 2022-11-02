package com.example.myquiz.quiz;

import org.jsoup.nodes.Element;

public class Question {
    final public String value;

    public Question(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("問題が空です。");
        }
        this.value = value;
    }

    public static Question getQuestionFromHtmlElement(final Element element) {
        final String questionString = element.getElementsByClass("qtext").text();
        return new Question(questionString);
    }
}
