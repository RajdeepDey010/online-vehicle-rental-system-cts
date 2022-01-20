package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleAvailableResponse {
<<<<<<< HEAD
    private int vehicleAvailableForCity;
    private String message;
    private List<VehicleInformation> vehicleInformationList;
    private int totalCitiesAvailable;
    private List<String> vehicleAvailableInCities;
=======
    private int totalVehicleAvailable;
    private List<VehicleInformation> vehicleInformationList;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}
