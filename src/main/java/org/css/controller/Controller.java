package org.css.controller;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {
    public static void execute(){
        //private static final Logger LOGGER = Logger.getLogger(Controller.class);

        try (Connection connection = MyConnection.getConnection()){
            System.out.println("Connection successful!");
            //Logger.info("You are connected");
        } catch (SQLException e) {
            System.out.println("Connection refused.");
            e.printStackTrace();
            //Logger.error("Issue connecting to CCS.", e);
            //System.exit(0);
        }
    }

}
