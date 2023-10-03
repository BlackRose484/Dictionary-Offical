package org.example;

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
}
