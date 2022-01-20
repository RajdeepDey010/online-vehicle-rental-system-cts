package com.cts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleSlotsResponse {
    private String message;
    private boolean success;
    private String vehicleRegistrationNumber;
    List<BookedSlot> bookedSlotList;
}
