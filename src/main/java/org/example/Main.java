package org.example;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        Dictionary dictionary = new Dictionary();
        DictionaryCommandline.dictionaryBasic(dictionary);
    }
}
