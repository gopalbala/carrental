package com.gb.carrental.model.reservation;

public class ChildSeat extends AddonDecorator {
    public ChildSeat(VehicleAddon vehicleAddon) {
        super(vehicleAddon);
    }

    @Override
    public double getCost() {
        return super.getCost() + 150;
    }
}
