package com.cts.model;

import com.cts.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponse {
    private boolean success;
    private String message;
    private UserType userType;
}
