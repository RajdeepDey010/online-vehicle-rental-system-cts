package com.cts.repository;

<<<<<<< HEAD
import com.cts.entitiy.BookingStatus;
=======
import com.cts.entities.BookingStatus;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusRepository extends CrudRepository<BookingStatus, String> {
    List<BookingStatus> findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted
<<<<<<< HEAD
            (String vehicleRegistrationNumber, String bookingStatus, boolean RideCompleted);

=======
            (String vehicleRegistrationNumber,String bookingStatus, boolean RideCompleted);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    List<BookingStatus> findAllByBookingStatus(String bookingStatus);

    List<BookingStatus> findAllByRideCompleted(boolean rideCompleted);

    List<BookingStatus> findAllByBookedUserEmail(String email);
}
