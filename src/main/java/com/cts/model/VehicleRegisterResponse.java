package com.cts.model;

<<<<<<< HEAD
import com.cts.entitiy.VehicleInformationEntity;
=======
import com.cts.entities.VehicleInformationEntity;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRegisterResponse {
<<<<<<< HEAD
    private boolean success;
=======
    private boolean registerSuccess;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    private String message;
    @JsonProperty("registeredVehicle")
    private VehicleInformationEntity vehicleInformationEntity;
}
