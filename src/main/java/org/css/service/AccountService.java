package org.css.service;

import org.css.model.Account;

public interface AccountService {
    int createAccount(Account account);
    int checkForAccount(Account account);

}
