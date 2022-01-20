package com.cts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VehicleBook {
    private String vehicleRegistrationNumber;
    private String vehicleName;
    private String city;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startDuration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date endDuration;
    private String bookedByUser;

    @Override
    public String toString() {
        return "VehicleBook{" +
                "vehicleRegistrationNumber='" + vehicleRegistrationNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", city='" + city + '\'' +
                ", startDuration=" + startDuration +
                ", endDuration=" + endDuration +
                ", bookedByUser='" + bookedByUser + '\'' +
                '}';
    }
}
