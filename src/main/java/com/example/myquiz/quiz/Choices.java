package com.example.myquiz.quiz;

import java.util.ArrayList;

public class Choices {
    public ArrayList<String> values;

    public Choices(ArrayList<String> values) {
        this.values = values;
    }

    public void add(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("受け取った文字列が空です");
        }
        this.values.add(value);
    }


}
