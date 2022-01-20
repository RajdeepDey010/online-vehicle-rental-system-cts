package com.cts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsResponse {
    private boolean success = false;
    private String message;
    private UserDetails userDetails;
}
