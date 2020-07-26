package com.gb.carrental.service;

import com.gb.carrental.model.reservation.Invoice;
import com.gb.carrental.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
