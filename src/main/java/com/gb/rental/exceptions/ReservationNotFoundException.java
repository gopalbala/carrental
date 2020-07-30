package com.gb.rental.exceptions;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
