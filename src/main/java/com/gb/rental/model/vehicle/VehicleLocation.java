package com.gb.rental.model.vehicle;

import com.gb.rental.model.common.Address;
import com.gb.rental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}
