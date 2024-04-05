package org.css.dao;

import org.css.controller.MyConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.css.model.Account;

public class AccountDaoJdbc implements AccountDao {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AccountDaoJdbc.class));
    public int createAccount(Account account) {
        LOGGER.log(Level.INFO, "Creating account from AccountDaoJdbc createAccount method.");

        if (checkForAccount(account) == 0){
            return 1;
        } else {
            final String usersQueryForUser = "INSERT INTO USERS(U_USERNAME, U_EMAIL, U_PASSWORD) VALUES(?,?,?)";

            try (Connection connection = MyConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(usersQueryForUser)) {
                int index = 0;
                LOGGER.log(Level.INFO, "sql INSERT statement initialized.");

                stmt.setString(++index, account.getUsername());
                stmt.setString(++index, account.getEmail());
                stmt.setString(++index, account.getPassword());

                if(stmt.execute()) return 0;
                else return 1;

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString());
            }
            return -1;
        }
    }
    public int checkForAccount(Account account){
        LOGGER.log(Level.INFO, "Checking for existing account...");

        // Query which needs parameters
        final String usersQueryForUser = "SELECT * FROM USERS WHERE U_USERNAME =?";

        // Connection to your database
        // Prepare Statement
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(usersQueryForUser)) {
            LOGGER.log(Level.INFO, "Initializing account search.");
            int index = 0;

            // Set Parameters
            stmt.setString(++index, account.getUsername());

            // Execute SQL query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                LOGGER.log(Level.INFO, "The following Account was found in the database: " + account.getUsername());
                return 0;
            } else {
                LOGGER.log(Level.INFO, "Account was not found in database.");
                return 1;
//                System.exit(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return -1;
    }
}
