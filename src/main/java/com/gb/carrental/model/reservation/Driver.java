package com.gb.carrental.model.reservation;

public class Driver extends AddonServiceDecorator {
    public Driver(AddonService addonService) {
        super(addonService);
    }

    @Override
    public double getCost() {
        return super.getCost() + 250;
    }
}
