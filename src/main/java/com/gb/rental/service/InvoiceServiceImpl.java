package com.gb.rental.service;

import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.ReservationStatus;
import com.gb.rental.model.reservation.VehicleReservation;

public class InvoiceServiceImpl implements InvoiceService {
    InvoiceServiceFactory invoiceServiceFactory = new InvoiceServiceFactory();

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        if (vehicleReservation.getStatus() == ReservationStatus.CANCELLED)
            return InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }
}
