package com.gb.rental.repository;

import com.gb.rental.model.reservation.VehicleInventory;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventoryRepository {
    List<VehicleInventory> vehicleInventoryList = new ArrayList<>();

    public VehicleInventory addBookingToInventory(VehicleInventory vehicleInventory) {
        vehicleInventoryList.add(vehicleInventory);
        return vehicleInventory;
    }
}
