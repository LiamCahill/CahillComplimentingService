package org.css.service;

import org.css.dao.AccountDao;
import org.css.dao.AccountDaoJdbc;
import org.css.model.Account;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AccountServiceImpl.class));

    boolean isLoggedIn = false;

    public AccountServiceImpl() {
        super();
    }

    public AccountDao dao = new AccountDaoJdbc();

    public int createAccount(Account account){
        LOGGER.log(Level.INFO, "Calling createAccount");
        return dao.createAccount(account);
    }

    public int checkForAccount(Account account){
        LOGGER.log(Level.INFO, "Checking for existing account");
        return dao.checkForAccount(account);
    }
}
