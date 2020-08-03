package com.gb.rental.model.reservation;

import com.gb.rental.model.common.Address;
import com.gb.rental.model.vehicle.VehicleType;
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
    private String accocatedVehicleId;
    private VehicleType vehicleType;
    private String invoiceId;
    private List<VehicleAddon> vehicleAddons;
    private List<AddonService> addonServices;
    private VehicleReservationType vehicleReservationType;
}
