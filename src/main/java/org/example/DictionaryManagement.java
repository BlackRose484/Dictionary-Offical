package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dictionary, Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the number of words: ");
        int numWords = sc.nextInt();
        while (numWords-- > 0) {
            String wordTarget = sc.next();
            String wordExplain = sc.nextLine();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.wordArr.add(word);

            try {
                // Kiểm tra xem từ đã tồn tại hay chưa
                String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                                    + " WHERE wordTarget = ?";

                PreparedStatement checkExist = connect.prepareStatement(CheckExist);
                checkExist.setString(1,wordTarget);
                ResultSet res = checkExist.executeQuery();
                res.next();
                int exist = res.getInt(1);


                if (exist == 0) {
                    String WordInsert = "INSERT INTO " + DBConnect.DB_NAME
                            + " (idx,wordTarget,wordMeaning) VALUES (?, ?, ?)";

                    PreparedStatement stmt = connect.prepareStatement(WordInsert);
                    connect.setAutoCommit(false);
                    stmt.setInt(1,1);
                    stmt.setString(2,wordTarget);
                    stmt.setString(3,wordExplain);
                    int a = stmt.executeUpdate();
                    connect.commit();
                } else {
                    System.out.println("Từ đã tồn tại trong từ điển, nghĩa cu từ sẽ được cập nhật");
                    String GetExplain = "SELECT * FROM " + DBConnect.DB_NAME
                                        + " WHERE wordTarget = ?";
                    PreparedStatement getExplain = connect.prepareStatement(GetExplain);
                    getExplain.setString(1,wordTarget);
                    ResultSet getExplainWord = getExplain.executeQuery();
                    getExplainWord.next();
                    String Explain = getExplainWord.getString(3) + "\n" + wordExplain;


                    String Update = "UPDATE " + DBConnect.DB_NAME + " SET wordMeaning = ? WHERE wordTarget = ?";
                    PreparedStatement update = connect.prepareStatement(Update);
                    connect.setAutoCommit(false);
                    update.setString(1,Explain);
                    update.setString(2,wordTarget);
                    update.executeUpdate();
                    connect.commit();

                    connect.setAutoCommit(false);

                }


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void removeFromCommandLine(Dictionary dictionary,Connection connect) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn xóa ");
        String wordDelete = sc.nextLine();
        try {
            String CheckExist = "SELECT COUNT(*) FROM " + DBConnect.DB_NAME
                    + " WHERE wordTarget = ?";

            PreparedStatement checkExist = connect.prepareStatement(CheckExist);
            checkExist.setString(1,wordDelete);
            ResultSet res = checkExist.executeQuery();
            res.next();
            int exist = res.getInt(1);
            if(exist == 0) {
                System.out.println("Không tồn tại từ này");
                return;
            } else {
                String Delete = "DELETE FROM " + DBConnect.DB_NAME
                                + " WHERE wordTarget = ?";
                PreparedStatement delete = connect.prepareStatement(Delete);
                delete.setString(1, wordDelete);
                connect.setAutoCommit(false);
                delete.executeUpdate();
                connect.commit();
            }
        } catch (Exception e) {
            System.out.println("No");
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
