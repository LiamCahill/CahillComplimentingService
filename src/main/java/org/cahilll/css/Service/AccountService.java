package org.cahilll.css.Service;

import java.util.ArrayList;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Model.Compliment;

public interface AccountService {

    public boolean loggedIn(Account account);

    public ArrayList<Compliment> retrieveCompliment(Account userAccount);

    public boolean sendCompliment(Account sender, String receiver, String compliment);


}
