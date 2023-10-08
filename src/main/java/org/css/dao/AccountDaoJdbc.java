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
            //todo Figure out why the connection is failing here.
            int index = 0;

            String sql1 = "SELECT * FROM USERS WHERE U_USERNAME = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, account.getUsername());
            if (statement1.executeUpdate() > 0) {
                System.out.println("Account already exists.");

                return 0;
            } else {
                //Add logic to initialize new account creation
                System.out.println("-----Creating your new account-----");
                String sql2 = "INSERT INTO ACCOUNT(U_USERNAME, U_EMAIL, U_PASSWORD) VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql2);

                statement.setString(++index, account.getUsername());
                statement.setString(++index, account.getEmail());
                statement.setString(++index, account.getPassword());


                if (statement.executeUpdate() > 0) {
                    System.out.println("Success.");
                    return 0;
                }
                System.out.println("failed");


            }
        } catch (SQLException e) {
            System.out.println("failing at SQL exception");
            e.printStackTrace();
        }

        return -1;
    }
}
