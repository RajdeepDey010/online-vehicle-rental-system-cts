package com.cts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.regex.Pattern;

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

    // Regular expression pattern for validating license number
    private static final String LICENSE_NUMBER_PATTERN = "^[\\w\\s-]+$";

    // Regular expression pattern for validating phone number
    private static final String PHONE_NUMBER_PATTERN = "^\\d{1,3}-\\d{10}$";

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        // Validate license number before setting the value
        if (validateLicenseNumber(vehicleRegistrationNumber)) {
            this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        } else {
            throw new IllegalArgumentException("Invalid license number format");
        }
    }

    public void setBookedByUser(String bookedByUser) {
        // Validate phone number before setting the value
        if (validatePhoneNumber(bookedByUser)) {
            this.bookedByUser = bookedByUser;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    private boolean validateLicenseNumber(String licenseNumber) {
        // Check if the license number matches the pattern
        return Pattern.matches(LICENSE_NUMBER_PATTERN, licenseNumber);
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        // Check if the phone number matches the pattern
        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }

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
