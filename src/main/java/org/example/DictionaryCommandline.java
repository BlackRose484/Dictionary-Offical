package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords(Dictionary dictionary, Connection connect) {

        System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
//        int i = 1;
//        for (Word x : dictionary.wordArr) {
//            System.out.printf("%-3s | %-15s | %-20s \n", String.valueOf(i), x.getWordTarget(), x.getWordExplain());
//            i++;
//        }

        try {
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select * from " + DBConnect.DB_NAME);
            while (res.next()) {
                String meaning = res.getString(3);
                String[] mean = meaning.split("\n");
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
                                res.getString(2),
                                mean[i]);
                    } else {
                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dictionaryBasic(Dictionary dictionary,Connection connect) throws FileNotFoundException {
        //DictionaryManagement.insertFromCommandline(dictionary,connect);
        //DictionaryManagement.insertFromFile(dictionary);
        // DictionaryCommandline.showAllWords(dictionary,connect);
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");

        System.out.print("Your action: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                DictionaryManagement.insertFromCommandline(dictionary,connect);
                break;
            case 2:
                DictionaryManagement.removeFromCommandLine(dictionary,connect);
                break;
            case 3:
                DictionaryManagement.insertFromCommandline(dictionary,connect);
                break;
            case 4:
                break;
            case 5:
                DictionaryCommandline.showAllWords(dictionary,connect);
                break;
            case 6:
                DictionaryCommandline.searchWord(connect);
                break;
        }
    }

    public static void suggestWord(String word, Connection connect) {
        try {
            System.out.println("Có vẻ từ bạn tìm không tồn tại, có phải ý bạn là: ");
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select * from " + DBConnect.DB_NAME
                                            + " where wordTarget like '" + word +"%'");
            System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
            while (res.next()) {
                String meaning = res.getString(3);
                String[] mean = meaning.split("\n");
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
                                res.getString(2),
                                mean[i]);
                    } else {
                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getWord (String word, Connection connect) {
        try {
            Statement smt = connect.createStatement();
            ResultSet res = smt.executeQuery("Select * from " + DBConnect.DB_NAME
                                                + " where wordTarget = '" + word + "'");
            System.out.printf("%-3s | %-15s | %-20s \n", "No", "English", "Vietnamese");
            if(res.next()) {
                String meaning = res.getString(3);
                String[] mean = meaning.split("\n");
                for (int i = 0; i < mean.length; i ++) {
                    if (i == 0) {
                        System.out.printf("%-3s | %-15s | %-20s \n", res.getInt(1),
                                res.getString(2),
                                mean[i]);
                    } else {
                        System.out.printf("%-3s   %-15s | %-20s \n", "", "", mean[i]);
                    }
                }
            } else {
                suggestWord(word, connect);
            }
        } catch (Exception e) {
            System.out.println("Loi truy xuat Db");
            System.out.println(e);
        }
    }

    public static void searchWord(Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần tìm ");
        String wordTarget = sc.next();
        getWord(wordTarget,connect);
    }
}
