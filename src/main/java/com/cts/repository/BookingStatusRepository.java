package com.cts.repository;

import com.cts.entities.BookingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusRepository extends CrudRepository<BookingStatus, String> {
    List<BookingStatus> findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted
            (String vehicleRegistrationNumber,String bookingStatus, boolean RideCompleted);
    List<BookingStatus> findAllByBookingStatus(String bookingStatus);

    List<BookingStatus> findAllByRideCompleted(boolean rideCompleted);

    List<BookingStatus> findAllByBookedUserEmail(String email);
}
