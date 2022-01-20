package com.cts.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "VehicleInformation")
public class VehicleInformationEntity {
    @Id
    private String vehicleRegistrationNumber;
    private String vehicleName;
    private String city;
    private float CostPerKiloMeter;

    @Override
    public String toString() {
        return "VehicleInformationEntity{" +
                "vehicleRegistrationNumber='" + vehicleRegistrationNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", city='" + city + '\'' +
                ", CostPerKiloMeter=" + CostPerKiloMeter +
                '}';
    }
}
