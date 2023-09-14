package org.css.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
    //private static final Logger LOGGER = Logger.getLogger(Connection.class);

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/CSS_DEV";

        Properties connectionProps = new Properties();
        connectionProps.put("root@localhost", "username");
        connectionProps.put("liamcahill", "password");

        //Logger.trace("Testing connection...");
        return DriverManager.getConnection(url, connectionProps);
    }

}
