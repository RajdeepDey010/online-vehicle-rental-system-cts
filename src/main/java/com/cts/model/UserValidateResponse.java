package com.cts.model;


import com.cts.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserValidateResponse {
    private String response;
    private String jwtToken;
    private boolean success;
    private UserType userType;
}
