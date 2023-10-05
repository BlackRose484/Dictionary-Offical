package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Formatter;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the number of words: ");
        int numWords = sc.nextInt();
        while (numWords-- > 0) {
            String wordTarget = sc.next();
            String wordExplain = sc.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.wordArr.add(word);
        }
    }

    public static void insertFromFile(Dictionary dictionary) throws FileNotFoundException {
        File file = new File("src/main/java/org/example/dictionaries.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            Word word = new Word(sc.next(), sc.nextLine().trim());
            dictionary.wordArr.add(word);
        }
    }
}
