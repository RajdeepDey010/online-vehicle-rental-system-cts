package com.cts.model;

import com.cts.enums.UserType;
import lombok.*;
/*
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private String name;
    private String phonenumber;
    private String licensenumber;
    private String address;
    private boolean blocked;
    private UserType userType;
}
*/

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDetails {
    @Pattern(regexp = "^[\\w\\s-]+$", message = "License number must contain only numbers, alphabets, spaces, and '-' symbol")
    private String licenseNumber;

    @Pattern(regexp = "^\\d{2}-\\d{10}$", message = "Phone number must be in countrycode-mobilenumber format (e.g., 91-8876522421)")
    private String phoneNumber;

    // Getters and setters

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}