package com.gb.carrental.repository;

public abstract class AccountRepository {
    public abstract void resetPassword(String userId, String password);
}
