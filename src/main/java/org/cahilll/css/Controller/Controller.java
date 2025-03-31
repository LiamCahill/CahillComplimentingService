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
import java.util.ArrayList;
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
            createMainWindow();
        }
    }

    private void createMainWindow(){
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            
            // Create window
            BasicWindow mainWindow = new BasicWindow("The Cahill Compliment Service");
            
            // Create and start GUI
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), 
                    new EmptySpace(TextColor.ANSI.BLUE));
            
            // Show the main menu
            showMainMenu(gui, mainWindow);
            
            // Start the GUI with the main window
            gui.addWindowAndWait(mainWindow);
            
            screen.stopScreen();
        } catch (IOException e) {
            log.error("Error initializing terminal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showMainMenu(WindowBasedTextGUI gui, BasicWindow mainWindow) {
            Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
            panel.addComponent(new Label("Please select the following options:"));
            panel.addComponent(new EmptySpace());
    
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
                    
                    // Pass the main window to showUserOptions
                    showUserOptions(gui, mainWindow);
                });
                
                Button cancelButton = new Button("Cancel", loginWindow::close);
                
                buttonPanel.addComponent(submitButton);
                buttonPanel.addComponent(cancelButton);
                loginPanel.addComponent(buttonPanel);
                
                loginWindow.setComponent(loginPanel);
                
                // Show the login window
                gui.addWindowAndWait(loginWindow);

            });
            
            Button exitButton = new Button("Exit", mainWindow::close);
            
            panel.addComponent(loginButton);
            panel.addComponent(exitButton);
            
            // Set window component
            mainWindow.setComponent(panel);
        }

    private void showUserOptions(WindowBasedTextGUI gui, BasicWindow mainWindow) {
        // Create a new panel that will replace the current content
        Panel optionsPanel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        // Add a title
        optionsPanel.addComponent(new Label("Welcome " + userAccount.getUsername() + "!"));
        optionsPanel.addComponent(new EmptySpace());
        optionsPanel.addComponent(new Label("Please select an option:"));
        optionsPanel.addComponent(new EmptySpace());
        
        Button retrieveButton = new Button("Retrieve a compliment", () -> {
            // Handle retrieving compliment
            System.out.println("Retrieving compliment...");
            showRetrieveCompliment(gui, mainWindow);
        });
        
        Button sendButton = new Button("Send a compliment", () -> {
            // Handle sending compliment
            System.out.println("Sending compliment...");
            sendCompliment(gui, mainWindow);
        });
        
        Button logoutButton = new Button("Logout", () -> {
            userAccount = null;
            // Return to main menu when logging out
            showMainMenu(gui, mainWindow);
        });
        
        optionsPanel.addComponent(retrieveButton);
        optionsPanel.addComponent(sendButton);
        optionsPanel.addComponent(logoutButton);
        
        // Set the new panel as the window's component
        mainWindow.setComponent(optionsPanel);
    }

    private void showRetrieveCompliment(WindowBasedTextGUI gui, BasicWindow mainWindow) {
        Panel retrievePanel = new Panel(new LinearLayout(Direction.VERTICAL));
        retrievePanel.addComponent(new Label("Retrieve a compliment"));
        retrievePanel.addComponent(new EmptySpace());

        Button retrieveComplimentButton = new Button("Retrieve a compliment", () -> {
            ArrayList<String> compliment = accountService.retrieveCompliment(userAccount);
            String complimentString = String.join("\n", compliment);

            // Handle no compliments found
            if (compliment.isEmpty()) {
                MessageDialog.showMessageDialog(
                    gui, 
                    "No compliments found",
                    complimentString,
                    MessageDialogButton.No);
    
            }

            MessageDialog.showMessageDialog(
                gui, 
                "Compliment Retrieved",
                complimentString,
                MessageDialogButton.OK);
        });

        Button backButton = new Button("Back", () -> {
            // Return to user options menu
            showUserOptions(gui, mainWindow);
        });

        retrievePanel.addComponent(retrieveComplimentButton);
        retrievePanel.addComponent(backButton);
        mainWindow.setComponent(retrievePanel);

    }

    private void sendCompliment(WindowBasedTextGUI gui, BasicWindow mainWindow) {
        Panel retrievePanel = new Panel(new LinearLayout(Direction.VERTICAL));
        retrievePanel.addComponent(new Label("Draft your compliment"));
        retrievePanel.addComponent(new EmptySpace());

        TextBox complimentField = new TextBox();
        TextBox recipientField = new TextBox();


        Button sendButton = new Button("Send", () -> {

            if (complimentField.getText().isEmpty()) {
                MessageDialog.showMessageDialog(
                    gui, 
                    "You must enter a compliment!",
                    "OK",
                    MessageDialogButton.No);
    
            } else if (recipientField.getText().isEmpty()) {
                MessageDialog.showMessageDialog(
                    gui, 
                    "You must enter a recipient",
                    "OK",
                    MessageDialogButton.No);
            }


            // Return to user options menu
            accountService.sendCompliment(userAccount, recipientField.getText(), complimentField.getText());
            showUserOptions(gui, mainWindow);
        });


        Button cancelButton = new Button("Cancel", () -> {
            showUserOptions(gui, mainWindow);
        });

        retrievePanel.addComponent(new Label("Recipient:"));
        retrievePanel.addComponent(recipientField);
        retrievePanel.addComponent(new EmptySpace());
        retrievePanel.addComponent(new Label("Compliment:"));
        retrievePanel.addComponent(complimentField);
        retrievePanel.addComponent(new EmptySpace());
        retrievePanel.addComponent(sendButton);
        retrievePanel.addComponent(cancelButton);
        mainWindow.setComponent(retrievePanel);

    }


}
