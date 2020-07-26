package com.gb.carrental.model.reservation;

public class Navigation extends AddonDecorator {

    public Navigation(VehicleAddon vehicleAddon) {
        super(vehicleAddon);
    }

    @Override
    public double getCost() {
        return super.getCost() + 500;
    }
}
