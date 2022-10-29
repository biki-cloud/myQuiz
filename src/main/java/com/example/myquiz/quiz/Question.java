package com.example.myquiz.quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question {
    final public String value;

    public Question(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("問題が空です。");
        }
        this.value = value;
    }
}
