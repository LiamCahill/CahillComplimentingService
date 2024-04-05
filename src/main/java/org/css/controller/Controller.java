package org.css.controller;

import org.css.dao.AccountDaoJdbc;
import org.css.model.Account;
import org.css.model.Compliment;
import org.css.service.AccountServiceImpl;
import org.css.service.AccountService;
import org.css.constants.Logo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Controller.class));
    private static Account userAccount = null;
    private static AccountService accountService = new AccountServiceImpl();

    public void console(){
        Logo logo = new Logo();
        System.out.println(logo);
        Scanner scanner = new Scanner(System.in);
        LOGGER.log(Level.INFO, "Creating 'admin' users.");
        userAccount = new Account("admin","admin@gmail.com","adminpw",null);

        try (Connection connection = MyConnection.getConnection()){
            System.out.println("Connection status: SUCCESSFUL");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        System.out.println("Please select an option from the following list:");
        System.out.println("[1].Check account status.");
        System.out.println("[2].Create an account.");
        System.out.println("[3].Exit the program.");

        // Build Layer one of the terminal menu here.
        int input = scanner.nextInt();
        switch (input){
            case 1:
                accountService.checkForAccount(userAccount);
                break;
            case 2:
                accountService.createAccount(userAccount);
                break;
            case 3:
                System.out.println("Exiting the program. Goodbye.");
                System.exit(0);

        }





    }

}
