package com.gb.rental.service;

import com.gb.rental.model.account.User;
import com.gb.rental.model.common.NotificationStatus;
import com.gb.rental.model.reservation.InvoiceNotification;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
