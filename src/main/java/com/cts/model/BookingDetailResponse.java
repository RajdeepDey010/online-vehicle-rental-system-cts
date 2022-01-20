package com.cts.model;

<<<<<<< HEAD
import com.cts.entitiy.BookingStatus;
=======
import com.cts.entities.BookingStatus;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDetailResponse {
    private boolean success;
    private String message;
    BookingStatus bookingStatus;
}
