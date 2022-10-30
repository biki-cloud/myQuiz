package com.example.myquiz.quiz;


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

    public void choicesContainsAnswer() {
        if (choices == null) {
            throw new NullPointerException("choicesがnullになっています。setChoicesを使用し、セットしてからこの関数を実行してください");
        }

        if (answer == null) {
            throw new NullPointerException("answerがnullになっています。SetAnswerを使用し、セットしてからこの関数を実行してください");
        }

        if (!choices.values.contains(answer.value)) {
            throw new IllegalStateException("選択肢(choices)の中に答え(answer)が入っていません。");
        }
    }

    @Override
    public String toString() {
        return "QuizContent {" +
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
