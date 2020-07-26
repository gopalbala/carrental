package com.gb.carrental.model.vehicle;

import com.gb.carrental.model.common.Address;
import com.gb.carrental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}
