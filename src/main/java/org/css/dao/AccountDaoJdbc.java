package org.css.dao;

import org.css.controller.MyConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.css.model.Account;

public class AccountDaoJdbc implements AccountDao {

    private static Logger LOGGER = Logger.getLogger(String.valueOf(AccountDaoJdbc.class));
    public int createAccount(Account account) {
        LOGGER.log(Level.INFO, "Creating account from AccountDaoJdbc createAccount method.");
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String usersQueryForUser = "SELECT * FROM USERS WHERE U_USERNAME = 'DCAHILL'";
            LOGGER.log(Level.INFO, "sql SELECT statement initialized.");

            PreparedStatement stmt = connection.prepareStatement(usersQueryForUser);
            //stmt.setString(1, account.getUsername());
            ResultSet rs = stmt.executeQuery(usersQueryForUser);

            if (rs.next()){
                LOGGER.log(Level.INFO, "Account was found in database already. Will not be creating new one.");
                System.out.println("Account already exists.");
                return 0;
            } else {
                LOGGER.log(Level.INFO, "Account was not found in database. Creating new one.");
                System.exit(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return -1;
    }
    public int checkForAccount(Account account){
        LOGGER.log(Level.INFO, "Checking for existing account...");

        // Query which needs parameters
        final String usersQueryForUser = "SELECT * FROM USERS WHERE U_USERNAME =?";

        // Connection to your database
        // Prepare Statement
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(usersQueryForUser); ) {
            LOGGER.log(Level.INFO, "Initializing account search.");
            int index = 0;

            // Set Parameters
            stmt.setString(++index, account.getUsername());

            // Execute SQL query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                LOGGER.log(Level.INFO, "Account found in database.");
                System.out.println("Account already exists.");
                return 0;
            } else {
                LOGGER.log(Level.INFO, "Account was not found in database.");
                System.exit(1);
            }


        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return -1;
    }
}
