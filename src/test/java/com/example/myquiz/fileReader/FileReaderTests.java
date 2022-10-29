package com.example.myquiz.fileReader;

import com.example.myquiz.utils.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileReaderTests {

    @Test
    void fileRead() throws IOException {
        FileReader fr = new FileReader();

        ClassLoader classLoader = getClass().getClassLoader();

        String text = fr.read(classLoader.getResource("quizFiles/CS2205/UNIT1/1.html").getFile());
        System.out.println(text);
    }

}
