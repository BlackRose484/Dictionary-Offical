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
        try{
            Formatter db = new Formatter("src/Database/db.txt");
            while (numWords-- > 0) {
                String wordTarget = sc.next();
                String wordExplain = sc.nextLine();
                db.format("%s   %s\n",wordTarget,wordExplain);
                Word word = new Word(wordTarget, wordExplain);
                dictionary.wordArr.add(word);
            }
            db.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
