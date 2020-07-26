package com.gb.carrental.service;

import com.gb.carrental.exceptions.InvalidVehicleIdException;
import com.gb.carrental.exceptions.ReservationNotFoundException;
import com.gb.carrental.exceptions.VehicleBookedException;
import com.gb.carrental.model.reservation.ReservationStatus;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.reservation.VehicleReservationType;
import com.gb.carrental.model.vehicle.HireableVehicle;
import com.gb.carrental.model.vehicle.VehicleLocation;
import com.gb.carrental.model.vehicle.VehicleStatus;
import com.gb.carrental.repository.UserRepository;
import com.gb.carrental.repository.VehicleRepository;
import com.gb.carrental.repository.VehicleReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepository();
    VehicleReservationRepository vehicleReservationRepository = new
            VehicleReservationRepository();
    VehicleRepository vehicleRepository = new VehicleRepository();

    VehicleReservationService vehicleReservationService = new
            VehicleReservationServiceImpl();

    @Override
    public VehicleReservation reserve(String barcode, String userId) throws InvalidVehicleIdException, VehicleBookedException {
        VehicleReservation vehicleReservation = new VehicleReservation();
        if (VehicleRepository.vehicleMap.get(barcode) == null) {
            throw new InvalidVehicleIdException("Invalid vehicle id.");
        }
        if (vehicleReservationService.isVehicleBooked(barcode,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2))) {
            throw new VehicleBookedException("Vehicle booked. Try another vehicle.");
        }

        return buildQuickReservation(barcode, userId);
    }

    @Override
    public VehicleReservation reserve(VehicleReservation vehicleReservation) {
        return null;
    }

    @Override
    public VehicleReservation cancel(String reservationId) {
        return null;
    }

    @Override
    public HireableVehicle pickupVehicle(VehicleReservation vehicleReservation) {
        return null;
    }

    @Override
    public void returnVehicle(String reservationId,
                              VehicleLocation vehicleLocation) throws ReservationNotFoundException {

        VehicleReservation vehicleReservation = vehicleReservationRepository
                .getVehicleReservation(reservationId);
        if (vehicleReservation == null) {
            throw new ReservationNotFoundException
                    ("Could not find reservation with id " + reservationId);
        }

        HireableVehicle vehicle = vehicleReservation.getVehicle();
        vehicle.setParkedLocation(vehicleLocation);
        vehicle.setVehicleStatus(VehicleStatus.AVAILALBE);
        vehicleReservation.setStatus(ReservationStatus.COMPLETED);
        vehicleReservation.setDropLocation(vehicleLocation.getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicle.getMileage());
    }

    @Override
    public List<HireableVehicle> getHiredVehicles(String userId) {

        return userRepository.getHiredVehicles(userId);
    }

    @Override
    public List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                                  LocalDateTime endDate) {
        return userRepository.getHiredVehicles(userId, startDate, endDate);
    }

    private VehicleReservation buildQuickReservation(String barcode, String userId) {

        HireableVehicle vehicle = VehicleRepository.vehicleMap.get(barcode);
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        VehicleReservation vehicleReservation = new VehicleReservation();
        vehicleReservation.setUsrId(userId);
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now());
        vehicleReservation.setDueDate(LocalDateTime.now().plusHours(2));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(
                vehicle.getParkedLocation().getAddress());
        vehicleReservation.setVehicle(vehicle);
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        return vehicleReservation;
    }

}
