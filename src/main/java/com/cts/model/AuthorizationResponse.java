package com.cts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationResponse {
    private boolean success;
    private String message;
}
