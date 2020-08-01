package com.gb.rental.service;

import com.gb.rental.TestData;
import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.VehicleInventory;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleInventoryRepository;
import com.gb.rental.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceServiceTest {
    @Test
    public void should_ComputeInvoice() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation = TestData.getVehicleReservation(user);
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 1600.0);
        assertEquals(invoice.getAddonCost(), 500.0);
        assertEquals(invoice.getTaxes(), 288.0);
        assertEquals(invoice.getTotal(), 1888.0);
    }
}
