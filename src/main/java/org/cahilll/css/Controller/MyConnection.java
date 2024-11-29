package org.cahilll.css.Controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.cahilll.css.Common.Constants.connectionURL;
import static org.cahilll.css.Common.Constants.userName;

public class MyConnection {
    private static final Logger LOGGER = Logger.getLogger("MyConnection.class");

    public static Connection getConnection() throws SQLException {
        /**
         * For troubleshooting: https://www.geeksforgeeks.org/java-database-connectivity-with-mysql/#
         */

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CSS_DEV",
                    "myuser", "mypassword");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return connection;

    }
}
