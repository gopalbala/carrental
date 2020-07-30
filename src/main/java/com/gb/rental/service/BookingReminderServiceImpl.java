package com.gb.rental.service;

import com.gb.rental.model.account.User;
import com.gb.rental.model.common.NotificationStatus;
import com.gb.rental.model.reservation.ReservationReminder;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleReservationRepository;

public class BookingReminderServiceImpl implements BookingReminderService {

    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public void notifyUser(ReservationReminder reservationReminder) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminder.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminder.setNotificationStatus(NotificationStatus.SENT);
    }
}
