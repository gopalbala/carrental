package com.gb.carrental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private String invoiceId;
    private String userId;
    private double usageCharges;
    private double addonCost;
    private double addonServicesCost;
    private double taxes;
    private double total;
}
