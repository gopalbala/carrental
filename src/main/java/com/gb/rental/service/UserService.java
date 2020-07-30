package com.gb.rental.service;

import com.gb.rental.exceptions.InvalidVehicleIdException;
import com.gb.rental.exceptions.ReservationNotFoundException;
import com.gb.rental.exceptions.VehicleBookedException;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.vehicle.VehicleLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException;

    VehicleReservation remoteReserve(VehicleReservation vehicleReservation);

    VehicleReservation cancel(String reservationId);

    HireableVehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<HireableVehicle> getHiredVehicles(String userId);

    List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                           LocalDateTime endDate);
}
