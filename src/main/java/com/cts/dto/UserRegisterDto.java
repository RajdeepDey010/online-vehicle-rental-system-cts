package com.cts.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserRegisterDto {
    private String name;
    private String email;
    @Pattern(regexp = "\\d{2}-\\d{10}", message = "Phone number must be in the format (country-code)-(number)")
    private String phno;
    @Size(min = 15, max = 15, message = "License number must be 15 characters long")
    @Pattern(regexp = "[A-Z]{2}_[A-Z0-9]{2}-\\w{9}", message = "License number must be in the format 2-character-state-code_RTO_Code-(License no given by RTO)")
    private String licenseno;
    private String address;
    private String password;
    private boolean blocked;
    private String userType;
    private String token;
}
