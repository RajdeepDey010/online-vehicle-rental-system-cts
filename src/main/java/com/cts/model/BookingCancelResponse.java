package com.cts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingCancelResponse {
    private boolean success;
    private String message;
}
