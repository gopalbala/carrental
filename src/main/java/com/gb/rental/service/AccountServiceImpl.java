package com.gb.rental.service;

import com.gb.rental.exceptions.AccountDoesNotExistsException;
import com.gb.rental.model.account.Account;
import com.gb.rental.model.account.AccountType;
import com.gb.rental.repository.AccountRepository;
import com.gb.rental.repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        return accountRepository.createAccount(account);
    }

    public void resetPassword(String userId, String password,
                              AccountType accountType) throws AccountDoesNotExistsException {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}
