package com.cts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleBookResponse {
    private boolean bookSuccess;
    private String bookingId;
    private String message;
}
