package com.gb.carrental.model.reservation;

public class PassengerScreen extends AddonDecorator {
    public PassengerScreen(VehicleAddon vehicleAddon) {
        super(vehicleAddon);
    }

    @Override
    public double getCost() {
        return super.getCost() + 100;
    }
}
