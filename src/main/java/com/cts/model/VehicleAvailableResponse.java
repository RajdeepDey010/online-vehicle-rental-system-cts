package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleAvailableResponse {
    private int vehicleAvailableForCity;
    private String message;
    private List<VehicleInformation> vehicleInformationList;
    private int totalCitiesAvailable;
    private List<String> vehicleAvailableInCities;
}
