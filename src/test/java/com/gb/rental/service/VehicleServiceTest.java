package com.gb.rental.service;

import com.gb.rental.TestData;
import com.gb.rental.model.vehicle.HireableVehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VehicleServiceTest {
    @Test
    public void addVehicleTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        assertNotNull(hireableVehicle);
    }
}
