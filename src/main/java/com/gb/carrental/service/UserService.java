package com.gb.carrental.service;

import com.gb.carrental.exceptions.InvalidVehicleIdException;
import com.gb.carrental.exceptions.ReservationNotFoundException;
import com.gb.carrental.exceptions.VehicleBookedException;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.HireableVehicle;
import com.gb.carrental.model.vehicle.VehicleLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    VehicleReservation scanToReserve(String barcode, String userId) throws InvalidVehicleIdException, VehicleBookedException;

    VehicleReservation remoteReserve(VehicleReservation vehicleReservation);

    VehicleReservation cancel(String reservationId);

    HireableVehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<HireableVehicle> getHiredVehicles(String userId);

    List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                           LocalDateTime endDate);
}
