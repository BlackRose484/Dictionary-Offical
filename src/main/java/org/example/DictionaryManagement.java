package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

            try {
                Connection connect = DBConnect.getConnection(DBConnect.DB_URL,
                                     DBConnect.USER_NAME,
                                     DBConnect.PASSWORD);

                String WordInsert = "INSERT INTO wordTest(idx,wordTarget,wordMeaning) VALUES (?, ?, ?)";

                PreparedStatement stmt = connect.prepareStatement(WordInsert);
                connect.setAutoCommit(false);
                stmt.setInt(1,1);
                stmt.setString(2,wordTarget);
                stmt.setString(3,wordExplain);
                int a = stmt.executeUpdate();
                connect.commit();

            } catch (Exception e) {
                System.out.println(e);
            }
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
