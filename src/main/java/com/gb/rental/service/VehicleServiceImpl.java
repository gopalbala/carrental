package com.gb.rental.service;

import com.gb.rental.exceptions.VehicleNotExistsException;
import com.gb.rental.model.reservation.VehicleInventory;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.repository.VehicleInventoryRepository;
import com.gb.rental.repository.VehicleRepository;

public class VehicleServiceImpl implements VehicleService {
    VehicleRepository vehicleRepository = new VehicleRepository();

    @Override
    public HireableVehicle getVehicleById(String id) {
        return VehicleRepository.vehicleMap.get(id);
    }

    @Override
    public HireableVehicle getVehicleByQrCode(String qrCode) {
        return VehicleRepository.vehicles.stream().filter(hireableVehicle ->
                hireableVehicle.getQrCode().equalsIgnoreCase(qrCode)).findAny().get();
    }

    @Override
    public HireableVehicle addVehicle(HireableVehicle hireableVehicle) {
        addToInventory(hireableVehicle);
        return vehicleRepository.addVehicle(hireableVehicle);
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
        VehicleInventoryRepository vehicleInventoryRepository = new VehicleInventoryRepository();
        vehicleInventoryRepository.removeFromInventory(new VehicleInventory(hireableVehicle));
        //Remove future bookings or reassign
    }

    private void addToInventory(HireableVehicle hireableVehicle) {
        VehicleInventory vehicleInventory = new VehicleInventory(hireableVehicle);
        VehicleInventoryRepository vehicleInventoryRepository = new VehicleInventoryRepository();
        vehicleInventoryRepository.addToInventory(vehicleInventory);
    }
}
