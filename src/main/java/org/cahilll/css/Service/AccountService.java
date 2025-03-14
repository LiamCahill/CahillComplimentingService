package org.cahilll.css.Service;

import org.cahilll.css.Model.Account;

public interface AccountService {

    public boolean loggedIn(Account account);

    public void retrieveCompliment(Account userAccount);


}
