package com.gb.carrental.model.reservation;

import com.gb.carrental.model.common.Address;
import com.gb.carrental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}
