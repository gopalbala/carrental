package com.gb.carrental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddonDecorator extends VehicleAddon {
    private VehicleAddon vehicleAddon;
}
