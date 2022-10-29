package com.example.myquiz.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public FileReader() {}

    /**
     * filepathを受け取り、中も文字列を返す
     * @param filepath 読み込むファイルパス
     * @return 中も文字列を返す
     */
    public String read(String filepath) {
        File fileObj = new File(filepath);
        StringBuilder allText = new StringBuilder();
        try {
            Scanner myReader = new Scanner(fileObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allText.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allText.toString();
    }
}
