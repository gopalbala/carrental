package com.gb.carrental.service;

import com.gb.carrental.model.account.AccountType;
import com.gb.carrental.repository.AccountRepository;
import com.gb.carrental.repository.AdminRepository;
import com.gb.carrental.repository.DriverRepository;
import com.gb.carrental.repository.UserRepository;

public class AccountRepositoryFactory {
    public static AccountRepository getAccountRepository(AccountType accountType) {
        switch (accountType) {
            case USER:
                return new UserRepository();
            case ADMIN:
                return new AdminRepository();
            case DRIVER:
                return new DriverRepository();
            default:
                return null;
        }
    }
}
