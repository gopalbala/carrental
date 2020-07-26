package com.gb.carrental.repository;

import com.gb.carrental.model.reservation.VehicleReservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleReservationRepository {
    public static List<VehicleReservation> vehicleReservations = new ArrayList<>();

    public static List<VehicleReservation>
    getVehicleByType(String type, LocalDateTime fromDate, LocalDateTime toDate) {
        return null;
    }
}
