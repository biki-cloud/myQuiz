package com.example.myquiz.quiz;


import java.util.ArrayList;

public class QuizContent {
    private Question question;
    private Choices choices;
    private Answer answer;

    public QuizContent() {}

    public QuizContent(Question question, Choices choices, Answer answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionContent{" +
                "question='" + question.value + '\'' +
                ", choices=" + choices.values +
                ", answer='" + answer.value + '\'' +
                '}';
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }


    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
