package org.css.dao;

import org.css.controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.css.model.Account;

public class AccountDaoJdbc implements AccountDao {

    private static Logger LOGGER = Logger.getLogger(String.valueOf(AccountDaoJdbc.class));

    public int createAccount(Account account) {
        LOGGER.log(Level.INFO, "Creating account from AccountDaoJdbc createAccount method.");
        try (Connection connection = MyConnection.getConnection()) {
            LOGGER.log(Level.INFO, "Inside AccountDaoJdbc createAccount connection try block");

            int index = 0;
            String sql1 = "SELECT * FROM USERS WHERE U_USERNAME = ?";
            LOGGER.log(Level.INFO, "sql SELECT statement initialized.");

            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, account.getUsername());

            // TODO:  executeUpdate() or executeLargeUpdate() with statements that produce result sets
            if (statement1.executeUpdate()> 0) {
                LOGGER.log(Level.INFO, "Account was found in database already. Will not be creating new one.");
                System.out.println("Account already exists.");
                return 0;
            } else {
                LOGGER.log(Level.INFO, "Account was not found in database. Creating new one.");
                String sql2 = "INSERT INTO ACCOUNT(U_USERNAME, U_EMAIL, U_PASSWORD) VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql2);

                statement.setString(++index, account.getUsername());
                statement.setString(++index, account.getEmail());
                statement.setString(++index, account.getPassword());


                if (statement.executeUpdate() > 0) {
                    LOGGER.log(Level.INFO, "New account successfully created.");
                    System.out.println("Success.");
                    return 0;
                }
                LOGGER.log(Level.INFO, "New account creation failed..");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return -1;
    }
}
