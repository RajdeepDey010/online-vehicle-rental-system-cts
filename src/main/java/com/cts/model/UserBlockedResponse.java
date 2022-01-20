package com.cts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBlockedResponse {
    private String email;
    private boolean blocked;
    private String message;
}
