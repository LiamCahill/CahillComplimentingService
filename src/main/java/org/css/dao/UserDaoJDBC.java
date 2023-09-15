package org.css.dao;

import org.css.controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.css.model.Account;

public class UserDaoJDBC {

    private static Logger LOGGER = Logger.getLogger(String.valueOf(UserDaoJDBC.class));

    public int createAccount(Account account) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;

            String sql1 = "SELECT * FROM ACCOUNT WHERE A_EMAIL = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, account.getUsername());
            if (statement1.executeUpdate() > 0) {
                System.out.println("Account already exists.");

                return 0;
            } else {
                //Add logic to initialize new account creation
                String sql = "INSERT INTO ACCOUNT()";
                System.out.println("-----Creating your new account-----");

                return 0;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
