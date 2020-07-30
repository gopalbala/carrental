package com.gb.rental.service;

import com.gb.rental.model.reservation.ReservationReminder;

public interface BookingReminderService {
    void notifyUser(ReservationReminder reservationReminder);
}
