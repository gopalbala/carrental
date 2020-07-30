package com.gb.rental.model.reservation;

import com.gb.rental.model.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleMonthlyCosts {
    public static Map<VehicleType, Double> vehicleMonthlyCost = new HashMap<>();

    static {
        vehicleMonthlyCost.put(VehicleType.BICYCLE, 800.0);
        vehicleMonthlyCost.put(VehicleType.MOTORCYCLE, 4000.0);
        vehicleMonthlyCost.put(VehicleType.HATCHBACK, 10000.0);
        vehicleMonthlyCost.put(VehicleType.SEDAN, 15000.0);
        vehicleMonthlyCost.put(VehicleType.SUV, 20000.0);
        vehicleMonthlyCost.put(VehicleType.TRUCK, 40000.0);
        vehicleMonthlyCost.put(VehicleType.THREEWHEELER, 20000.0);
        vehicleMonthlyCost.put(VehicleType.VAN, 20000.0);
    }
}
