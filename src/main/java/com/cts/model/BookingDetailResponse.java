package com.cts.model;

import com.cts.entities.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDetailResponse {
    private boolean success;
    private String message;
    BookingStatus bookingStatus;
}
