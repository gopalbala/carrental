package com.gb.carrental.service;

import com.gb.carrental.model.account.AccountType;
import com.gb.carrental.repository.AccountRepository;

public class AccountService {

    public void resetPassword(String userId,
                              AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
    }
}
