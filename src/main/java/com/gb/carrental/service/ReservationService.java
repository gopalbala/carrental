package com.gb.carrental.service;

import com.gb.carrental.model.reservation.VehicleReservation;

public interface ReservationService {
    VehicleReservation reserveVehicle(VehicleReservation vehicleReservation);
}
