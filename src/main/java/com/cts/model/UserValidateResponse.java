package com.cts.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserValidateResponse {
    private String response;
    private String jwtToken;
    private boolean loginSuccess;
}
