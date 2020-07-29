package com.gb.carrental.service;

import com.gb.carrental.model.account.User;
import com.gb.carrental.model.common.NotificationStatus;
import com.gb.carrental.model.reservation.InvoiceNotification;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.repository.UserRepository;
import com.gb.carrental.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
