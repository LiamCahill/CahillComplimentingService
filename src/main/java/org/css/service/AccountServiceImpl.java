package org.css.service;

import org.css.dao.AccountDao;
import org.css.dao.AccountDaoJdbc;
import org.css.model.Account;

public class AccountServiceImpl implements AccountService {
    boolean isLoggedIn = false;

    public AccountServiceImpl() {
        super();
    }

    public AccountDao dao = new AccountDaoJdbc();

    public int createAccount(Account account){
        System.out.println("Processing in ASI");
        return dao.createAccount(account);
    }
}
