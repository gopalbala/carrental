package com.gb.carrental.service;

import com.gb.carrental.exceptions.InvalidVehicleIdException;
import com.gb.carrental.exceptions.ReservationNotFoundException;
import com.gb.carrental.exceptions.VehicleBookedException;
import com.gb.carrental.model.common.NotificationStatus;
import com.gb.carrental.model.reservation.*;
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

    VehicleReservationService vehicleReservationService = new
            VehicleReservationServiceImpl();
    InvoiceService invoiceService = new InvoiceServiceImpl();
    InvoiceNotificationService invoiceNotificationService =
            new InvoiceNotificationServiceImpl();

    @Override
    public VehicleReservation scanToReserve(String barcode, String userId) throws InvalidVehicleIdException, VehicleBookedException {
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
    public VehicleReservation remoteReserve(VehicleReservation vehicleReservation) {
        VehicleReservationRepository.vehicleReservationMap
                .put(vehicleReservation.getReservationId(), vehicleReservation);
        vehicleReservation.getVehicle().setVehicleStatus(VehicleStatus.BOOKED);
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        return vehicleReservation;
    }

    @Override
    public VehicleReservation cancel(String reservationId) {
        VehicleReservation vehicleReservation = VehicleReservationRepository
                .vehicleReservationMap.get(reservationId);
        vehicleReservation.setStatus(ReservationStatus.CANCELLED);
        vehicleReservation.getVehicle().setVehicleStatus(VehicleStatus.AVAILALBE);
        vehicleReservation.setDropLocation(vehicleReservation.getVehicle().
                getParkedLocation().getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicleReservation.getVehicle().getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        return vehicleReservation;
    }

    @Override
    public HireableVehicle pickupVehicle(VehicleReservation vehicleReservation) {
        vehicleReservation.setStartMileage(vehicleReservation.getVehicle().getMileage());
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.getVehicle().setVehicleStatus(VehicleStatus.BOOKED);
        return vehicleReservation.getVehicle();
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

    private InvoiceNotification buildInvoiceNotification(Invoice invoice) {
        InvoiceNotification invoiceNotification = new InvoiceNotification();
        invoiceNotification.setReservationId(invoice.getReservationId());
        invoiceNotification.setUserId(invoice.getUserId());
        invoiceNotification.setCreatedDate(LocalDateTime.now());
        invoiceNotification.setNotificationStatus(NotificationStatus.PENDING);
        return invoiceNotification;
    }

}
