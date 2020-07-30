package com.gb.rental.service;

import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
