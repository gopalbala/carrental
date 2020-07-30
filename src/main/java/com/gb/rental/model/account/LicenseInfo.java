package com.gb.rental.model.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LicenseInfo {
    private String licenceNumber;
    private LocalDateTime issueDate;
    private LocalDateTime expiryDate;
    private String issuedAtPlace;
    private String issuedInState;
    private String issuedInCountry;
    private LicenseType licenseType;
}
