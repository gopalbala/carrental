package com.gb.rental.repository;

import com.gb.rental.model.reservation.VehicleInventory;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventoryRepository {
    public static List<VehicleInventory> vehicleInventoryList = new ArrayList<>();

    public VehicleInventory addToInventory(VehicleInventory vehicleInventory) {
        vehicleInventoryList.add(vehicleInventory);
        return vehicleInventory;
    }

    public void removeFromInventory(VehicleInventory vehicleInventory) {
        vehicleInventoryList.removeIf(vi -> vi.getVehicle().getQrCode().equalsIgnoreCase(
                vehicleInventory.getVehicle().getQrCode()
        ));
    }
}
