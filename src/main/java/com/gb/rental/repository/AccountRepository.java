package com.gb.rental.repository;

import com.gb.rental.exceptions.AccountDoesNotExistsException;
import com.gb.rental.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}
