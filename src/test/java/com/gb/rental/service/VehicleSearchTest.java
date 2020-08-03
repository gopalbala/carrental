package com.gb.rental.service;

import com.gb.rental.TestData;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.vehicle.VehicleType;
import com.gb.rental.repository.VehicleInventoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleSearchTest {
    @Test
    public void ShouldFindVehicleByType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        assertEquals(2, vehicleList.size());
    }

    @Test
    public void ShouldFindOneVehicleByType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now());
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3));
        assertEquals(1, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleByType() {

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now().minusDays(1));
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setFromDate(LocalDateTime.now().minusHours(5));
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setDueDate(LocalDateTime.now().plusDays(2));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleBySeats() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(6,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFind1VehicleBySeats() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(5,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(1, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search("make", "model",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFind1VehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search("Maruti", "Swift",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(1, vehicleList.size());
    }
}
