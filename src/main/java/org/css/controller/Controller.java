package org.css.controller;

import org.css.dao.AccountDaoJdbc;
import org.css.model.Account;
import org.css.model.Compliment;
import org.css.service.AccountServiceImpl;
import org.css.service.AccountService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private static Logger LOGGER = Logger.getLogger(String.valueOf(Controller.class));
    private static Account userAccount = null;
    private static AccountService accountService = new AccountServiceImpl();

    public void console(){
        //private static final Logger LOGGER = Logger.getLogger(Controller.class);
        LOGGER.log(Level.INFO, "Creating console 'driver' users.");
        userAccount = new Account();
        userAccount.setUsername("lim");
        userAccount.setPassword("cah");
        userAccount.setEmail("cah@gmail.com");


        System.out.println("Creating a connection...");
        try (Connection connection = MyConnection.getConnection()){
            System.out.println("Connection: Successful");
            //Logger.info("You are connected");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }


        LOGGER.log(Level.INFO, "Creating test account in Controller.java");
        String testUsername = "username";
        String testPassword = "password";
        String testEmail = "username@gmail.com";
        ArrayList<Compliment> testCompliments = new ArrayList<>();
        userAccount = new Account(testUsername,testEmail,testPassword, testCompliments);
        accountService.createAccount(userAccount);


    }

}
