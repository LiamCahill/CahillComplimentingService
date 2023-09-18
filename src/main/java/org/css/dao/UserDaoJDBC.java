package org.css.dao;

import org.css.controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.css.model.Account;

public class UserDaoJDBC implements UserDao {

    private static Logger LOGGER = Logger.getLogger(String.valueOf(UserDaoJDBC.class));

    public int createAccount(Account account) {
        try (Connection connection = MyConnection.getConnection()) {
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
                String sql2 = "INSERT INTO ACCOUNT(U_USERNAME, U_EMAIL, U_PHONE_NUMBER, U_PASSWORD) VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql2);

                statement.setString(++index, account.getUsername());
                statement.setString(++index, account.getPassword());


                return 0;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
