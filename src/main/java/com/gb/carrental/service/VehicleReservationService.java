package com.gb.carrental.service;

import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleReservationService {
    List<VehicleReservation> getReservations(VehicleType vehicleType);

    boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate);
}
