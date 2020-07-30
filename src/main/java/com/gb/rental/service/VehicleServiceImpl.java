package com.gb.rental.service;

import com.gb.rental.exceptions.VehicleNotExistsException;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.repository.VehicleRepository;

public class VehicleServiceImpl implements VehicleService {

    @Override
    public HireableVehicle addVehicle(HireableVehicle hireableVehicle) {
        return VehicleRepository.vehicleMap.putIfAbsent(hireableVehicle.getId(), hireableVehicle);
    }

    @Override
    public void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException {
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleId);
        if (hireableVehicle == null)
            throw new VehicleNotExistsException("Vehicle with id " + vehicleId + "not found");
        hireableVehicle.setQrCode(qrCode);
    }

    @Override
    public void removeVehicle(String vehicleId) throws VehicleNotExistsException {
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleId);
        if (hireableVehicle == null)
            throw new VehicleNotExistsException("Vehicle with id " + vehicleId + "not found");
        VehicleRepository.vehicleMap.remove(vehicleId);
        //Remove future bookings or reassign
    }
}
