package com.gb.carrental.model.reservation;

public class OnDemandTowing extends AddonServiceDecorator {
    public OnDemandTowing(AddonService addonService) {
        super(addonService);
    }

    @Override
    public double getCost() {
        return super.getCost() + 200;
    }
}
