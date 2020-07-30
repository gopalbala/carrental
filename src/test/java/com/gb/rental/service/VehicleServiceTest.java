package com.gb.rental.service;

import com.gb.rental.TestData;
import com.gb.rental.exceptions.VehicleNotExistsException;
import com.gb.rental.model.vehicle.HireableVehicle;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceTest {
    @Test
    public void addVehicleTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        assertNotNull(hireableVehicle);
    }

    @Test
    public void updateQrCodeTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        String qrCode = UUID.randomUUID().toString();
        hireableVehicle.setQrCode(qrCode);
        vehicleService.updateQrCode(hireableVehicle.getId(), qrCode);
        HireableVehicle altered = vehicleService.getVehicleById(hireableVehicle.getId());
        assertEquals(qrCode, altered.getQrCode());
    }

    @Test
    public void getVehicleByIdTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        assertNotNull(vehicle);
    }

    @Test
    public void getVehicleByQrCodeTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleByQrCode(hireableVehicle.getQrCode());
        assertNotNull(vehicle);
    }

    @Test
    public void removeVehicleTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        vehicleService.removeVehicle(hireableVehicle.getId());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        assertNull(vehicle);
    }
}
