package com.gb.rental.service;

import com.gb.rental.exceptions.AccountDoesNotExistsException;
import com.gb.rental.model.account.Account;
import com.gb.rental.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistsException;
}
