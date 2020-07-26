package com.gb.carrental.model.reservation;

import com.gb.carrental.model.account.Driver;
import com.gb.carrental.model.common.Address;
import com.gb.carrental.model.vehicle.HireableVehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VehicleReservation {
    private String reservationId;
    private String usrId;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private Address pickupLocation;
    private Address dropLocation;
    private double startMileage;
    private double endMileage;
    private HireableVehicle vehicle;
    private String invoiceId;
    private List<Driver> drivers;
    private List<VehicleAddon> vehicleAddons;
    private List<AddonService> addonServices;
    private VehicleReservationType vehicleReservationType;
}
