package org.cahilll.css.Service;

import java.util.ArrayList;

import org.cahilll.css.Dao.AccountDao;
import org.cahilll.css.Dao.AccountDaoJdbc;
import org.cahilll.css.Model.Account;

public class AccountServiceImpl implements AccountService {

    boolean isLoggedIn = false;

    public AccountServiceImpl() {
        super();
    }

    public AccountDao dao = new AccountDaoJdbc();


    public boolean loggedIn(Account account) {
        isLoggedIn = dao.validateAccount(account);
        return isLoggedIn;
    }

    public void retrieveCompliment(Account userAccount) {
        ArrayList<String> compliments = dao.retrieveCompliment(userAccount);
        System.out.println(compliments);
    }

}
