package org.cahilll.css.Dao;

import java.util.ArrayList;

import org.cahilll.css.Model.Account;

public interface AccountDao {

    public boolean validateAccount(Account account);

    public ArrayList<String> retrieveCompliment(Account userAccount);

}
