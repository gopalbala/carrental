package com.gb.carrental.model.reservation;

import com.gb.carrental.model.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleHourlyCosts {
    public static Map<VehicleType, Double> vehicleFixedCost = new HashMap<>();

    static {
        vehicleFixedCost.put(VehicleType.BICYCLE, 5.0);
        vehicleFixedCost.put(VehicleType.MOTORCYCLE, 20.0);
        vehicleFixedCost.put(VehicleType.HATCHBACK, 50.0);
        vehicleFixedCost.put(VehicleType.SEDAN, 150.0);
        vehicleFixedCost.put(VehicleType.SUV, 200.0);
        vehicleFixedCost.put(VehicleType.TRUCK, 250.0);
        vehicleFixedCost.put(VehicleType.THREEWHEELER, 100.0);
        vehicleFixedCost.put(VehicleType.VAN, 100.0);
    }
}
