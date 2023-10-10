package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Connection Connect = DBConnect.connectDB();
        Dictionary dictionary = new Dictionary();
        while(true) {
            DictionaryCommandline.dictionaryBasic(dictionary, Connect);
        }
    }
}
