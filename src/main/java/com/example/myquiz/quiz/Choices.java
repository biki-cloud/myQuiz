package com.example.myquiz.quiz;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Choices {
    public ArrayList<String> values;

    public Choices(ArrayList<String> values) {
        this.values = values;
    }

    /**
     * 文字列の最後に.がついていた場合は削除し、文を返す。ついていない場合はそのままの文字列を返す。
     * @param sentence 対象も文字列
     * @return 加工後の文字列を返す
     */
    public static String removeLastDotFromSentence(final String sentence) {
         if (sentence.charAt(sentence.length() - 1) == '.') {
             return sentence.substring(0, sentence.length() - 1);
         }
         return sentence;
    }

    public void add(String value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("受け取った文字列が空です");
        }
        this.values.add(value);
    }


    public static Choices getChoicesFromHtmlElement(final Element element) {
        final Choices choices = new Choices(new ArrayList<>());
        for (Element ele : element.getAllElements()) {
            final String className = ele.className();
            final String textOfElement = ele.text();
            if (className.equals("flex-fill ml-1") || className.equals("ml-1")) {
                choices.add(removeLastDotFromSentence(textOfElement));
            }
        }
        return choices;
    }

}
