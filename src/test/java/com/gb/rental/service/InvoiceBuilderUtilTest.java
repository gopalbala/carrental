package com.gb.rental.service;

import com.gb.rental.TestData;
import com.gb.rental.exceptions.InvalidVehicleIdException;
import com.gb.rental.exceptions.VehicleBookedException;
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

public class InvoiceBuilderUtilTest {
    @Test
    public void shouldBuildCancelledInvoice()
            throws VehicleBookedException, InvalidVehicleIdException {
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
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Invoice invoice = InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);

        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 50);
        assertEquals(invoice.getTaxes(), 9);
        assertEquals(invoice.getTotal(), 59);
    }
}
