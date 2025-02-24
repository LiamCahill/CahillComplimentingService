package org.cahilll.css.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cahilll.css.Common.Constants;
import org.cahilll.css.Model.Account;
import org.cahilll.css.Service.AccountService;
import org.cahilll.css.Service.AccountServiceImpl;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
@Slf4j
public class Controller {

    private static final Logger LOGGER = Logger.getLogger("Controller");
    private static Account userAccount = null;
    private static AccountService accountService = new AccountServiceImpl();
    public void console(){

        try (Scanner scanner = new Scanner(System.in)) {
            try (Connection connection = MyConnection.getConnection()){
                LOGGER.info("Connection successful");
            } catch (SQLException e) {
                System.out.println("Connection refused.");
                e.printStackTrace();
                System.exit(0);
            }

            userAccount = new Account();
            userAccount.setEmail("dec");
            userAccount.setPassword("lan");

            System.out.println("Please select an option from the following list:");
            System.out.println("[1].Logon.");

            String input = scanner.nextLine();
            System.out.println(input);

            if (input.equals("1")){
                System.out.println("Please enter in your username.");
                String username = scanner.nextLine();
                System.out.println("Please enter in your password.");
                String password = scanner.nextLine();

                Account unconfirmedAccount = new Account(username, password);
                System.out.println("Logged in status = " + accountService.loggedIn(unconfirmedAccount));
                if (!accountService.loggedIn(unconfirmedAccount)) {
                    System.out.println("Login failed. Please exit the application and retry.");
                    System.exit(0);
                }

                System.out.println("Please select from the following list:");
                System.out.println("[1] Retrieve a compliment");
                System.out.println("[2] Send a compliment");
                System.out.println("[3] Logout.");

                String accountOptions = scanner.nextLine();

                try {
                    Integer.parseInt(accountOptions);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid option.");
                }

                // if (accountOptions.equals("1")){
                //     accountService
                // }
            }
        }
    }

}
