package com.cts.model;

import com.cts.enums.UserType;
import lombok.*;

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
