package com.cts.utils;

import com.cts.repository.BookingStatusRepository;
import com.cts.entities.BookingStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
public class RideAvailabilityCheckThread implements Runnable {

    private BookingStatusRepository bookingStatusRepository;


    public RideAvailabilityCheckThread(BookingStatusRepository bookingStatusRepository) {
        this.bookingStatusRepository = bookingStatusRepository;
    }

    @Override
    public void run() {
        log.info("Starting Thread for searching Ride with ended Time");
        while (true) {
            try {
                List<BookingStatus> bookingStatusRepositoryList =
                        bookingStatusRepository.findAllByRideCompleted(false);

                for (BookingStatus bookingStatus : bookingStatusRepositoryList) {
                    Date now = new Date();
                    if (now.after(bookingStatus.getEndTime())) {
                        log.info("Ride EndTime for vehicle: {}",
                                bookingStatus.getVehicleRegistrationNumber());
                        bookingStatus.setRideCompleted(true);
                        bookingStatusRepository.save(bookingStatus);
                    }
                }

                Thread.sleep(10000);
            } catch (Exception exception) {
                log.error("Exception Occurred while checking booking database: {}", exception.getMessage());
            }
        }
    }
}
