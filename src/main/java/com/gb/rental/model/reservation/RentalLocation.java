package com.gb.rental.model.reservation;

import com.gb.rental.model.common.Address;
import com.gb.rental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}
