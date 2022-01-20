package com.cts.model;

import com.cts.entitiy.VehicleInformationEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRegisterResponse {
    private boolean success;
    private String message;
    @JsonProperty("registeredVehicle")
    private VehicleInformationEntity vehicleInformationEntity;
}
