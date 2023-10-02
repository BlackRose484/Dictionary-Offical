package org.example;

import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap di:");
        int numWords = sc.nextInt();
        while (numWords-- > 0) {
            String wordTarget = sc.next();
            String wordExplain = sc.next();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.wordArr.add(word);
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        insertFromCommandline(dictionary);
        DictionaryCommandline.showAllWords(dictionary);
    }
}
