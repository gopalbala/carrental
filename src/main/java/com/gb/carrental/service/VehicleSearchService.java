package com.gb.carrental.service;

import com.gb.carrental.model.vehicle.HireableVehicle;

import java.util.List;

public interface VehicleSearchService {
    List<HireableVehicle> searchByType(String type);

    List<HireableVehicle> searchByModel(String model);
}
