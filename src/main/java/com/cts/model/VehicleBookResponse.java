package com.cts.model;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.util.List;

@Getter
@Setter
public class VehicleBookResponse {
    private boolean success;
    private String bookingId;
    private String message;
    private String vehicleRegistrationNumber;
    private List<BookedSlot> bookedVehicleSlots;
=======
@Getter
@Setter
public class VehicleBookResponse {
    private boolean bookSuccess;
    private String bookingId;
    private String message;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}
