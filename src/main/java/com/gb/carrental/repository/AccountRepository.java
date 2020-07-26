package com.gb.carrental.repository;

import com.gb.carrental.exceptions.AccountDoesNotExistsException;
import com.gb.carrental.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}
