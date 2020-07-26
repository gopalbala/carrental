package com.gb.carrental.model.reservation;

import com.gb.carrental.model.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleDailyCosts {
    public static Map<VehicleType, Double> vehicleFixedCost = new HashMap<>();

    static {
        vehicleFixedCost.put(VehicleType.BICYCLE, 50.0);
        vehicleFixedCost.put(VehicleType.MOTORCYCLE, 200.0);
        vehicleFixedCost.put(VehicleType.HATCHBACK, 800.0);
        vehicleFixedCost.put(VehicleType.SEDAN, 1000.0);
        vehicleFixedCost.put(VehicleType.SUV, 1500.0);
        vehicleFixedCost.put(VehicleType.TRUCK, 2500.0);
        vehicleFixedCost.put(VehicleType.THREEWHEELER, 1000.0);
        vehicleFixedCost.put(VehicleType.VAN, 1000.0);
    }
}
