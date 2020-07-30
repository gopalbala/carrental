package com.gb.rental.service;

import com.gb.rental.model.vehicle.HireableVehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleSearchService {
    List<HireableVehicle> searchByType(String type, LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> searchByModel(String make,
                                        String model, LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> searchByType(int seats, LocalDateTime fromDate, LocalDateTime toDate);
}
