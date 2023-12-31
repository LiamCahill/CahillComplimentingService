package org.css.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
    //private static final Logger LOGGER = Logger.getLogger(Connection.class);

    public static Connection getConnection() throws SQLException {
        //source for troubleshooting: https://www.geeksforgeeks.org/java-database-connectivity-with-mysql/#
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CSS_DEV",
                    "root", "liamcahill");

            // mydb is database
            // mydbuser is name of database
            // mydbuser is password of database
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        //Logger.trace("Testing connection...");
        return connection;
    }

}
