package com.gb.carrental.service;

import com.gb.carrental.model.reservation.ReservationReminder;

public interface BookingReminderService {
    void notifyUser(ReservationReminder reservationReminder);
}
