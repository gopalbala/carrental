package com.gb.rental.model.reservation;

public class DeepCleaning extends AddonServiceDecorator {
    public DeepCleaning(AddonService addonService) {
        super(addonService);
    }

    @Override
    public double getCost() {
        return super.getCost() + 300;
    }
}
