package com.example.myquiz.quiz;


import java.util.ArrayList;

public class Question {
    private String question = "";
    private ArrayList<String> choices = new ArrayList<>();
    private String answer = "";
    public Question() {}

    public Question(String question, ArrayList<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionContent{" +
                "question='" + question + '\'' +
                ", choices=" + choices +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void addChoices(String choice) {
        choices.add(choice);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
