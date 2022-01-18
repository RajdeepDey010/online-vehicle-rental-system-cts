package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleAvailableResponse {
    private int totalVehicleAvailable;
    private List<VehicleInformation> vehicleInformationList;
}
