package com.gb.carrental.service;

import com.gb.carrental.model.reservation.InvoiceNotification;
import com.gb.carrental.model.reservation.ReservationReminder;

public interface NotificationService {
    void notifyUser(InvoiceNotification invoice);

    void notifyUser(ReservationReminder reservationReminder);
}
