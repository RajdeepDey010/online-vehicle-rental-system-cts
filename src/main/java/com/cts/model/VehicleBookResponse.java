package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleBookResponse {
    private boolean success;
    private String bookingId;
    private String message;
    private String vehicleRegistrationNumber;
    private List<BookedSlot> bookedVehicleSlots;
}
