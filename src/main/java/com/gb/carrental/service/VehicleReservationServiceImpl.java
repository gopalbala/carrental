package com.gb.carrental.service;

import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.VehicleType;
import com.gb.carrental.repository.VehicleReservationRepository;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleReservationServiceImpl implements VehicleReservationService {
    VehicleReservationRepository vehicleReservationRepository = new
            VehicleReservationRepository();

    @Override
    public List<VehicleReservation> getReservations(VehicleType vehicleType) {
        return vehicleReservationRepository.getVehicleReservations(vehicleType);
    }

    @Override
    public boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleReservationRepository.vehicleReservations
                .stream().anyMatch(vehicleReservation ->
                        vehicleReservation.getAccocatedVehicleId().equalsIgnoreCase(qrCode) &&
                                !vehicleReservation.getFromDate().isAfter(fromDate) &&
                                !vehicleReservation.getDueDate().isBefore(toDate));
    }
}
