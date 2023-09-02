package org.css.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
    //private static final Logger LOGGER = Logger.getLogger(Connection.class);

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:8080";

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "password");

        Connection conn = DriverManager.getConnection(url, props);
        //Logger.trace("Testing connection...");
        return conn;
    }

}
