package org.cahilll.css.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cahilll.css.Common.Constants;
import org.cahilll.css.Model.Account;
import org.cahilll.css.Service.AccountService;
import org.cahilll.css.Service.AccountServiceImpl;
import org.springframework.stereotype.Component;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import java.io.IOException;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
@Slf4j
public class Controller {

    private static Account userAccount = null;
    private static AccountService accountService = new AccountServiceImpl();
    public void console(){

        try (Scanner scanner = new Scanner(System.in)) {
            try (Connection connection = MyConnection.getConnection()){
                log.info("Connection successful");
            } catch (SQLException e) {
                System.out.println("Connection refused.");
                e.printStackTrace();
                System.exit(0);
            }

            try {
                Terminal terminal = new DefaultTerminalFactory().createTerminal();
                Screen screen = new TerminalScreen(terminal);
                screen.startScreen();
                System.out.println("the matrix has you");
                
                // Create window
                BasicWindow window = new BasicWindow("The Cahill Compliment Service");
                
                // Create panel
                Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
                panel.addComponent(new Label("Please select the the following options:"));
                panel.addComponent(new EmptySpace());

                // Create GUI once - keep this instance
                MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), 
                    new EmptySpace(TextColor.ANSI.BLUE));

                // Add buttons
                Button loginButton = new Button("Login", () -> {
                    BasicWindow loginWindow = new BasicWindow("Login");
                    Panel loginPanel = new Panel(new LinearLayout(Direction.VERTICAL));
                    
                    // Add username field
                    loginPanel.addComponent(new Label("Username:"));
                    TextBox usernameField = new TextBox();
                    loginPanel.addComponent(usernameField);
                    
                    // Add password field (using password input)
                    loginPanel.addComponent(new Label("Password:"));
                    TextBox passwordField = new TextBox().setMask('*');
                    loginPanel.addComponent(passwordField);

                    // Add login and cancel buttons
                    Panel buttonPanel = new Panel(new LinearLayout(Direction.HORIZONTAL));

                    Button submitButton = new Button("Login", () -> {
                        String username = usernameField.getText();
                        String password = passwordField.getText();
                        
                        Account unconfirmedAccount = new Account(username, password);
                        
                        if (!accountService.loggedIn(unconfirmedAccount)) {
                            MessageDialogButton msgButton = MessageDialog.showMessageDialog(
                                gui, "Login Failed", "Invalid username or password. Please try again.",
                                MessageDialogButton.OK);
                            return;
                        }
                        
                        userAccount = unconfirmedAccount;
                        loginWindow.close();
                        
                        // Show options in a new dialog
                        showUserOptions(gui);
                    });
                    
                    Button cancelButton = new Button("Cancel", loginWindow::close);
                    
                    buttonPanel.addComponent(submitButton);
                    buttonPanel.addComponent(cancelButton);
                    loginPanel.addComponent(buttonPanel);
                    
                    loginWindow.setComponent(loginPanel);
                    
                    // Show the login window
                    gui.addWindowAndWait(loginWindow);

                });

                // Add buttons
                Button viewButton = new Button("View Compliments", () -> {
                    // Handle view compliments
                    System.out.println("Viewing compliments...");
                });
                
                Button sendButton = new Button("Send Compliment", () -> {
                    // Handle send compliment
                    System.out.println("Sending compliment...");
                });
                
                Button settingsButton = new Button("Account Settings", () -> {
                    // Handle account settings
                    System.out.println("Opening settings...");
                });
                
                Button exitButton = new Button("Exit", window::close);
                
                panel.addComponent(loginButton);
                panel.addComponent(viewButton);
                panel.addComponent(sendButton);
                panel.addComponent(settingsButton);
                panel.addComponent(exitButton);
                
                // Set window component
                window.setComponent(panel);
                
                // Use the existing gui instance - DELETE THE DUPLICATE DEFINITION HERE
                gui.addWindowAndWait(window);

                screen.stopScreen();
            } catch (IOException e) {
                log.error("Error initializing terminal: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void showUserOptions(WindowBasedTextGUI gui) {
        BasicWindow optionsWindow = new BasicWindow("Welcome " + userAccount.getUsername());
        Panel optionsPanel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        optionsPanel.addComponent(new Label("Please select an option:"));
        optionsPanel.addComponent(new EmptySpace());
        
        Button retrieveButton = new Button("Retrieve a compliment", () -> {
            // Handle retrieving compliment
            System.out.println("Retrieving compliment...");
        });
        
        Button sendButton = new Button("Send a compliment", () -> {
            // Handle sending compliment
            System.out.println("Sending compliment...");
        });
        
        Button logoutButton = new Button("Logout", () -> {
            userAccount = null;
            optionsWindow.close();
        });
        
        optionsPanel.addComponent(retrieveButton);
        optionsPanel.addComponent(sendButton);
        optionsPanel.addComponent(logoutButton);
        
        optionsWindow.setComponent(optionsPanel);
        gui.addWindowAndWait(optionsWindow);
    }

}
