package com.gb.carrental.service;

import com.gb.carrental.model.account.User;
import com.gb.carrental.model.reservation.Invoice;
import com.gb.carrental.model.reservation.VehicleFixedCosts;
import com.gb.carrental.model.reservation.VehicleMonthlyCosts;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.HireableVehicle;
import com.gb.carrental.repository.UserRepository;

import java.time.Duration;
import java.util.UUID;

public class MonthInvoiceService implements InvoiceService {
    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration =
                Duration.between(vehicleReservation.getReturnDate(),
                        vehicleReservation.getFromDate());
        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours % 24);

        double months = Math.ceil(days % 30);

        HireableVehicle hireableVehicle = vehicleReservation.getVehicle();

        double monthlyCost = VehicleMonthlyCosts.
                vehicleMonthlyCost.get(hireableVehicle.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double addonServiceCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        invoice.setAddonCost(vehicleAddonCost);
        invoice.setAddonServicesCost(addonServiceCost);
        double rentalCost = days * monthlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}
