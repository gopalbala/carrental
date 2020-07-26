package com.gb.carrental.service;

import com.gb.carrental.model.reservation.VehicleReservationType;

public class InvoiceServiceFactory {
    public InvoiceService getInvoiceService(VehicleReservationType vehicleReservationType) {
        switch (vehicleReservationType) {
            case FOUR_HOURS:
            case EIGHT_HOURS:
                return new PackageInvoiceServiceImpl();
            case DAY:
                return new DayInvoiceService();
            case MONTH:
                return new MonthInvoiceService();
            case HOURLY:
            default:
                return new AdhocInvoiceService();
        }
    }
}
