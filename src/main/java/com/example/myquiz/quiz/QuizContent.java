package com.example.myquiz.quiz;


public class QuizContent {
    final private Question question;
    final private Choices choices;
    final private Answer answer;

    public QuizContent(Question question, Choices choices, Answer answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        choicesContainsAnswer();
    }

    /**
     * 選択肢の中に答えが含まれているかチェックするメソッド
     * choices(選択肢クラス）とanswer(答えクラス)が正常にセットされてからこの
     * メソッドを実行してください。
     * 選択肢の中に答えが含まれていない場合はIllegalStateExceptionを投げます。
     */
    private void choicesContainsAnswer() {
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


    public Choices getChoices() {
        return choices;
    }



    public Answer getAnswer() {
        return answer;
    }

}
