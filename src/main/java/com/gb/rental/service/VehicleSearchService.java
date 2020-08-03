package com.gb.rental.service;

import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleSearchService {
    List<HireableVehicle> search(VehicleType vehicleType, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(String make, String city,
                                 String model, LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(int seats, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);
}
