package org.css.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //todo: I need to figure out how I want to store the dev credentials. The plain-text solution below isn't realistic.
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CSS_DEV",
                    "root", "liamcahill");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return connection;
    }

}
