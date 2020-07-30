package com.gb.rental.repository;

import com.gb.rental.model.account.AccountType;

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
