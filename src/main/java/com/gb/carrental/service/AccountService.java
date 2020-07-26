package com.gb.carrental.service;

import com.gb.carrental.exceptions.AccountDoesNotExistsException;
import com.gb.carrental.model.account.Account;
import com.gb.carrental.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistsException;
}
