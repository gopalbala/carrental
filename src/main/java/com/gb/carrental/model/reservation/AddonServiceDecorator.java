package com.gb.carrental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddonServiceDecorator extends AddonService {
    private AddonService addonService;
}
