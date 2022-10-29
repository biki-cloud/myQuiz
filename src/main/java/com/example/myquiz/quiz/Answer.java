package com.example.myquiz.quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Answer {
    final public String value;

    public Answer(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("受け取った文字列が空です。");
        }
        this.value = value;
    }
}
