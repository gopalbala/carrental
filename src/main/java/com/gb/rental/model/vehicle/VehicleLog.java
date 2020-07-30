package com.gb.rental.model.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleLog {
    private String vehicleLogId;
    private String vehicleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String description;
    private VehicleLogType vehicleLogType;
}
