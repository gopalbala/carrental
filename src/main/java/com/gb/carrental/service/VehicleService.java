package com.gb.carrental.service;

import com.gb.carrental.exceptions.VehicleNotExistsException;
import com.gb.carrental.model.vehicle.HireableVehicle;

public interface VehicleService {
    HireableVehicle addVehicle(HireableVehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleNotExistsException;
}
