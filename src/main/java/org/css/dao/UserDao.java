package org.css.dao;

import org.css.model.Account;

import java.util.ArrayList;

public interface UserDao {

    int createAccount(Account account);

    //Compliment receiveCompliment(Account account)
    //Compliment giveCompliment(Account account)

    //For when a user wants to find others in the system, or is unsure another specific user's usernames.
    //ArrayList<Account> fineOtherUsers(Account account);
}
