package com.gb.rental.service;

import com.gb.rental.exceptions.InvalidVehicleIdException;
import com.gb.rental.exceptions.ReservationNotFoundException;
import com.gb.rental.exceptions.VehicleBookedException;
import com.gb.rental.model.common.NotificationStatus;
import com.gb.rental.model.reservation.*;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.vehicle.VehicleLocation;
import com.gb.rental.model.vehicle.VehicleStatus;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleRepository;
import com.gb.rental.repository.VehicleReservationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepository();
    VehicleReservationRepository vehicleReservationRepository = new
            VehicleReservationRepository();

    VehicleReservationService vehicleReservationService = new
            VehicleReservationServiceImpl();
    InvoiceService invoiceService = new InvoiceServiceImpl();
    InvoiceNotificationService invoiceNotificationService =
            new InvoiceNotificationServiceImpl();

    @Override
    public VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException {
        if (VehicleRepository.vehicleMap.get(qrCode) == null) {
            throw new InvalidVehicleIdException("Invalid vehicle id.");
        }
        if (vehicleReservationService.isVehicleBooked(qrCode,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2))) {
            throw new VehicleBookedException("Vehicle booked. Try another vehicle.");
        }
        return buildQuickReservation(qrCode, userId);
    }

    @Override
    public VehicleReservation remoteReserve(VehicleReservation vehicleReservation) {
        VehicleReservationRepository.vehicleReservationMap
                .put(vehicleReservation.getReservationId(), vehicleReservation);
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        //Notify vehicle inventory
        return vehicleReservation;
    }

    @Override
    public VehicleReservation cancel(String reservationId) {
        VehicleReservation vehicleReservation = VehicleReservationRepository
                .vehicleReservationMap.get(reservationId);
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        vehicleReservation.setStatus(ReservationStatus.CANCELLED);
        //Notify vehicle inventory
        vehicleReservation.setDropLocation(hireableVehicle.
                getParkedLocation().getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(hireableVehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        return vehicleReservation;
    }

    @Override
    public HireableVehicle pickupVehicle(VehicleReservation vehicleReservation) {
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        vehicleReservation.setStartMileage(hireableVehicle.getMileage());
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        hireableVehicle.setVehicleStatus(VehicleStatus.BOOKED);
        return hireableVehicle;
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

        HireableVehicle vehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        vehicle.setParkedLocation(vehicleLocation);
        vehicle.setVehicleStatus(VehicleStatus.AVAILALBE);
        vehicleReservation.setStatus(ReservationStatus.COMPLETED);
        vehicleReservation.setDropLocation(vehicleLocation.getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
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

    private VehicleReservation buildQuickReservation(String qrCode, String userId) {

        HireableVehicle vehicle = VehicleRepository.vehicleMap.get(qrCode);
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

        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(qrCode);
        vehicleReservation.setAccocatedVehicleId(hireableVehicle.getId());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        return vehicleReservation;
    }

    private InvoiceNotification buildInvoiceNotification(Invoice invoice) {
        InvoiceNotification invoiceNotification = new InvoiceNotification();
        invoiceNotification.setReservationId(invoice.getReservationId());
        invoiceNotification.setUserId(invoice.getUserId());
        invoiceNotification.setCreatedDate(LocalDateTime.now());
        invoiceNotification.setNotificationStatus(NotificationStatus.PENDING);
        return invoiceNotification;
    }

}
