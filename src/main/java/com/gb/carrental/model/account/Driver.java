package com.gb.carrental.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver extends Account {
    private LicenseInfo licenseInfo;
}
