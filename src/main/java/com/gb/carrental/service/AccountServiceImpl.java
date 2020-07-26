package com.gb.carrental.service;

import com.gb.carrental.exceptions.AccountDoesNotExistsException;
import com.gb.carrental.model.account.Account;
import com.gb.carrental.model.account.AccountType;
import com.gb.carrental.repository.AccountRepository;
import com.gb.carrental.repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        return null;
    }

    public void resetPassword(String userId, String password,
                              AccountType accountType) throws AccountDoesNotExistsException {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}
