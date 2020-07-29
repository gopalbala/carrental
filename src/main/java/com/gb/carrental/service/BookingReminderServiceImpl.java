package com.gb.carrental.service;

import com.gb.carrental.model.account.User;
import com.gb.carrental.model.common.NotificationStatus;
import com.gb.carrental.model.reservation.ReservationReminder;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.repository.UserRepository;
import com.gb.carrental.repository.VehicleReservationRepository;

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
