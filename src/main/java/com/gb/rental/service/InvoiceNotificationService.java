package com.gb.rental.service;

import com.gb.rental.model.reservation.InvoiceNotification;

public interface InvoiceNotificationService {
    void notifyUser(InvoiceNotification invoice);
}
