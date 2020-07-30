package com.gb.rental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AddonService {
    private String id;
    private String name;
    private String description;
    private double cost;
}
