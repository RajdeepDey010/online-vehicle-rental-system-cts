package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VehicleInformation {
    private String vehicleRegistrationNumber;
    private String vehicleName;
    private String city;
    private float costPerKilometer;
    private Date availableAfter;
}
