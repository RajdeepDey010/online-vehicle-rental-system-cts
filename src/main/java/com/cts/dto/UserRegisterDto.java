package com.cts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String name;
    private String email;
    private String phno;
    private String licenseno;
    private String address;
    private String password;
    private boolean blocked;
    private String userType;
    private String token;
}
