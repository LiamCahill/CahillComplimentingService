package org.cahilll.css.Dao;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Model.Compliment;
import org.cahilll.css.Controller.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountDaoJdbc implements AccountDao {

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
            String compliment_query = "SELECT * FROM COMPLIMENTS WHERE C_RECEIVER = ? AND `C_USED` = 0";
            log.info("Retrieving compliments for user: {}", userAccount.getUsername());

            int compliment_count = getTotalComplimentCount(userAccount);
            int readCompliments = getReadComplimentCount(userAccount);
            int unreadCompliments =  getUnreadComplimentCount(userAccount);

            PreparedStatement statement_compliment = connection.prepareStatement(compliment_query);
            statement_compliment.setString(++index, userAccount.getUsername());

            ResultSet result_compliment = statement_compliment.executeQuery();
            if(result_compliment != null) {
                log.info("Total, read, unread compliments for user: {} are {} / {} / {}", userAccount.getUsername(), compliment_count, readCompliments, unreadCompliments);

                // while(result_compliment.next()) {
                //     compliments.add(result_compliment.getString("C_MESSAGE"));
                // }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compliments;
    }

    @Override
    public boolean sendCompliment(Account sender, String receiver, String compliment) {

        if (!checkUserExists(receiver)) {
            log.error("User does not exist");
            // TODO: throw an exception and/or show a message to the user in new gui
            return false;
        }

        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "INSERT INTO COMPLIMENTS (C_SENDER, C_RECEIVER, C_MESSAGE) VALUES (?, ?, ?)";
            log.info("Sending compliments to user: {}", receiver);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(++index, sender.getUsername());
            statement.setString(++index, receiver);
            statement.setString(++index, compliment);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getTotalComplimentCount(Account userAccount) {
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

    private int getUnreadComplimentCount(Account userAccount) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "SELECT COUNT(*) FROM COMPLIMENTS WHERE C_RECEIVER = ? AND `C_USED` = 0";

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

    private int getReadComplimentCount(Account userAccount) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "SELECT COUNT(*) FROM COMPLIMENTS WHERE C_RECEIVER = ? AND `C_USED` = 1";

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

    private boolean checkUserExists(String username) {
        try (Connection connection = MyConnection.getConnection()) {
            int index = 0;
            String sql = "SELECT * FROM USERS WHERE U_USERNAME = ?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(++index, username);

            ResultSet result = statement.executeQuery();
            log.info("Checking if user exists: {}", username);
            log.info("Result: {}", result);
            if (result.next()) {
                return result.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
