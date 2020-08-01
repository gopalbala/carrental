package com.gb.rental.service;

import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.VehicleDailyCosts;
import com.gb.rental.model.reservation.VehicleFixedCosts;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.repository.UserRepository;

import java.time.Duration;
import java.util.UUID;

public class DayInvoiceService implements InvoiceService {

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusDays(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());
        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double dailyCost = VehicleDailyCosts.
                vehicleDailyCost.get(vehicleReservation.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(vehicleReservation.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        invoice.setAddonCost(vehicleAddonCost);
        double addonServiceCost = AddonCostUtil.computeServiceCost(vehicleReservation);
        invoice.setAddonServicesCost(addonServiceCost);
        double rentalCost = days * dailyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}
