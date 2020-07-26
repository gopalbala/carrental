package com.gb.carrental.service;

import com.gb.carrental.model.reservation.Invoice;
import com.gb.carrental.model.reservation.VehicleReservation;

public class InvoiceServiceImpl implements InvoiceService {
    InvoiceServiceFactory invoiceServiceFactory = new InvoiceServiceFactory();

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }


}
