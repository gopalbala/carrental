package com.gb.carrental.service;

import com.gb.carrental.model.account.User;
import com.gb.carrental.model.reservation.Invoice;
import com.gb.carrental.model.reservation.VehicleFixedCosts;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.HireableVehicle;
import com.gb.carrental.repository.UserRepository;

import java.util.UUID;

public class InvoiceBuilderUtil {
    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        HireableVehicle hireableVehicle = vehicleReservation.getVehicle();
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        invoice.setUsageCharges(fixedCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(fixedCost + taxes);
        return invoice;
    }
}
