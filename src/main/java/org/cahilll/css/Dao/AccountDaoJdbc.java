package org.cahilll.css.Dao;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


    @Override
    public ArrayList<String> retrieveCompliment(Account userAccount) {
        ArrayList<String> compliments = new ArrayList<>();
        
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String compliment_query = "SELECT * FROM COMPLIMENTS WHERE C_RECEIVER = ? ";
            int compliment_count = getComplimentCount(userAccount);

            PreparedStatement statement_compliment = connection.prepareStatement(compliment_query);
            statement_compliment.setString(++index, userAccount.getUsername());

            ResultSet result_compliment = statement_compliment.executeQuery();
            if(result_compliment != null) {
                System.out.println(compliment_count + " compliment(s) found!");
                while(result_compliment.next()) {
                    compliments.add(result_compliment.getString("C_MESSAGE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compliments;
    }

    private int getComplimentCount(Account userAccount) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "SELECT COUNT(*) FROM COMPLIMENTS WHERE C_RECEIVER = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(++index, userAccount.getUsername());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
