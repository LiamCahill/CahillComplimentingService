package org.css.controller;

import org.css.controller.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        System.out.printf("the matrix has you\n");
//
//        Controller controller = new Controller();
//        controller.Execute();

        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CSS_DEV",
                    "root", "liamcahill");

            System.out.println("Successful connection!!");

            // mydb is database
            // mydbuser is name of database
            // mydbuser is password of database
        }
        catch (Exception exception) {
            System.out.println(exception);
        }



    }
}