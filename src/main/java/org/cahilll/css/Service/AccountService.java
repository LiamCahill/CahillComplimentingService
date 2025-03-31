package org.cahilll.css.Service;

import java.util.ArrayList;

import org.cahilll.css.Model.Account;

public interface AccountService {

    public boolean loggedIn(Account account);

    public ArrayList<String> retrieveCompliment(Account userAccount);

    public void sendCompliment(Account sender, String receiver, String compliment);


}
