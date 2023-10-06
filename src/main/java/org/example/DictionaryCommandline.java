package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dictionary) {

        System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
//        int i = 1;
//        for (Word x : dictionary.wordArr) {
//            System.out.printf("%-3s | %-15s | %-20s \n", String.valueOf(i), x.getWordTarget(), x.getWordExplain());
//            i++;
//        }

        try {
            Connection connect = DBConnect.getConnection(DBConnect.DB_URL,
                                                         DBConnect.USER_NAME,
                                                         DBConnect.PASSWORD);
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select * from wordTest");
            while (res.next()) {
                System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
                                                             res.getString(2),
                                                             res.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dictionaryBasic(Dictionary dictionary) throws FileNotFoundException {
        DictionaryManagement.insertFromCommandline(dictionary);
        //DictionaryManagement.insertFromFile(dictionary);
        DictionaryCommandline.showAllWords(dictionary);
    }
}
