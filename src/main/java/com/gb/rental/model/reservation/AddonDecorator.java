package com.gb.rental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddonDecorator extends VehicleAddon {
    public AddonDecorator(VehicleAddon vehicleAddon) {
        this.vehicleAddon = vehicleAddon;
    }

    private VehicleAddon vehicleAddon;

    public double getCost() {
        return vehicleAddon.getCost();
    }
}
