package org.cahilll.css.Dao;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


public class AccountDaoJdbc implements AccountDao {
    private static Logger LOGGER = Logger.getLogger("AccountDaoJdbc");


    @Override
    public boolean validateAccount(Account account) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "SELECT * FROM USERS WHERE U_USERNAME = ? AND U_PASSWORD = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(++index, account.getUsername());
            statement.setString(++index, account.getPassword());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }
}
