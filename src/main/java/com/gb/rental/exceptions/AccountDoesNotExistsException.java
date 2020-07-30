package com.gb.rental.exceptions;

public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }
}
