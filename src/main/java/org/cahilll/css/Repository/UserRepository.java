package org.cahilll.css.Repository;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    public Optional<Account> findByUsername(String username) {
        try (Connection connection = MyConnection.getConnection()) {
            String sql = "SELECT * FROM USERS WHERE U_USERNAME = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Account account = new Account();
                account.setUsername(result.getString("U_USERNAME"));
                account.setEmail(result.getString("U_EMAIL")); 
                account.setPassword(result.getString("U_PASSWORD"));
                return Optional.of(account);
            }
            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
