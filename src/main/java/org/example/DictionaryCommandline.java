package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dictionary) {

        File db = new File("src/Database/db.txt");
        try{
            Scanner sc = new Scanner(db);
            while(sc.hasNext()) {
                System.out.println(sc.next());
            }
        } catch (Exception e) {
            System.out.println("Loi database");
        }

//        System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
//        int i = 1;
//        for (Word x : dictionary.wordArr) {
//            System.out.printf("%-3s | %-15s | %-20s \n", String.valueOf(i), x.getWordTarget(), x.getWordExplain());
//            i++;
//        }
    }

    public static void dictionaryBasic(Dictionary dictionary) throws IOException {
        DictionaryManagement.insertFromCommandline(dictionary);
        DictionaryCommandline.showAllWords(dictionary);
    }
}
