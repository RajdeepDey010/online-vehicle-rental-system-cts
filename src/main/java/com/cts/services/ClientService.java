package com.cts.services;

import com.cts.repository.BookingStatusRepository;
import com.cts.repository.VehicleInformationRepository;
import com.cts.config.ApplicationProperties;
import com.cts.dto.BookingCancelDto;
import com.cts.entities.BookingStatus;
import com.cts.entities.VehicleInformationEntity;
import com.cts.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    private VehicleInformationRepository vehicleInformationRepository;
    private BookingStatusRepository bookingStatusRepository;
    private ApplicationProperties applicationProperties;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public ClientService(VehicleInformationRepository vehicleInformationRepository,
                         BookingStatusRepository bookingStatusRepository,
                         ApplicationProperties applicationProperties,
                         HttpServletRequest httpServletRequest) {
        this.vehicleInformationRepository = vehicleInformationRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.applicationProperties = applicationProperties;
        this.httpServletRequest = httpServletRequest;
    }

    public VehicleAvailableResponse searchVehicle(VehicleSearch vehicleSearch) {

        List<VehicleInformation> vehicleInformationList = new ArrayList<>();
        Iterable<VehicleInformationEntity> vehicleInformationEntityList =
                vehicleInformationRepository.findAllByCity(vehicleSearch.getCity());


        for (VehicleInformationEntity vehicleAvailability : vehicleInformationEntityList) {
            VehicleInformation vehicleInformationEntity = new VehicleInformation();

            vehicleInformationEntity.setVehicleName(vehicleAvailability.getVehicleName());
            vehicleInformationEntity.setCity(vehicleAvailability.getCity());
            vehicleInformationEntity.setVehicleRegistrationNumber(vehicleAvailability.getVehicleRegistrationNumber());
            vehicleInformationEntity.setCostPerKilometer(vehicleAvailability.getCostPerKiloMeter());

            vehicleInformationList.add(vehicleInformationEntity);
        }
        VehicleAvailableResponse vehicleAvailableResponse = new VehicleAvailableResponse();
        vehicleAvailableResponse.setTotalVehicleAvailable(vehicleInformationList.size());
        vehicleAvailableResponse.setVehicleInformationList(vehicleInformationList);
        return vehicleAvailableResponse;
    }

    public VehicleBookResponse bookVehicle(VehicleBook vehicleBookInput) {

        VehicleBookResponse vehicleBookResponse = new VehicleBookResponse();
        vehicleBookResponse.setBookSuccess(false);

        try {

            VehicleInformationEntity vehicleInformationEntity = getVehicleInformation(vehicleBookInput.getVehicleRegistrationNumber());

            if (null != vehicleInformationEntity &&
                    null != vehicleBookInput.getStartDuration()
                    && null != vehicleBookInput.getEndDuration()) {

                Date now = new Date();
                Date startTime = vehicleBookInput.getStartDuration();
                Date endTime = vehicleBookInput.getEndDuration();


                if (!vehicleInformationEntity.getCity().equalsIgnoreCase(vehicleBookInput.getCity())) {
                    vehicleBookResponse.setMessage("Vehicle Not Available for selected City");
                } else if (startTime.after(now) && endTime.after(now) && startTime.before(endTime)) {

                    // check booking max Duration
                    if(endTime.getTime() - startTime.getTime() > applicationProperties.getBookingMaxDuration() * 60 * 1000){
                        log.info("Booking Duration Time Limit Reached. booking not Available for {} minutes",
                                applicationProperties.getBookingMaxDuration());
                        String hours = Double.toString(applicationProperties.getBookingMaxDuration()/60.0);

                        vehicleBookResponse.setMessage("booking duration limit is for " + hours + " hours");
                    }
                    else{
                        // check if booking already Present
                        boolean timeSlotAvailable = true;
                        List<BookingStatus> bookingStatusList =
                                bookingStatusRepository.
                                        findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted(
                                                vehicleBookInput.getVehicleRegistrationNumber(),
                                                "booked", false);

                        for (BookingStatus bookingStatus : bookingStatusList) {
                            Date bookedStartTime = bookingStatus.getStartTime();
                            Date bookedEndTime = bookingStatus.getEndTime();
                            // check condition for timeslot overlap
                            if (startTime.before(bookedEndTime) && endTime.after(bookedStartTime)) {
                                timeSlotAvailable = false;
                                break;
                            }
                        }
                        if (bookingStatusList.size() == 0) {
                            timeSlotAvailable = true;
                        }

                        if (!timeSlotAvailable) {
                            log.info("Time Slot Not Available for Vehicle: {}", vehicleBookInput.getVehicleRegistrationNumber());
                            vehicleBookResponse.setMessage("Time Slot Not Available. Vehicle Already Registered");
                        } else {
                            BookingStatus bookingStatus = new BookingStatus();

                            bookingStatus.setVehicleRegistrationNumber(vehicleBookInput.getVehicleRegistrationNumber());
                            bookingStatus.setVehicleName(vehicleInformationEntity.getVehicleName());
                            bookingStatus.setCity(vehicleInformationEntity.getCity());
                            bookingStatus.setStartTime(vehicleBookInput.getStartDuration());
                            bookingStatus.setEndTime(vehicleBookInput.getEndDuration());
                            bookingStatus.setBookingStatus("booked");
                            bookingStatus.setPaymentStatus("unposted");
                            bookingStatus.setTotalDistanceCovered(0);
                            bookingStatus.setBookingTime(new Date());
                            bookingStatus.setRideCompleted(false);
                            bookingStatus.setBookedUserEmail(httpServletRequest.getHeader("email"));

                            bookingStatus = bookingStatusRepository.save(bookingStatus);


                            vehicleBookResponse.setBookSuccess(true);
                            vehicleBookResponse.setBookingId(bookingStatus.getBookingId());
                            vehicleBookResponse.setMessage("Booking for vehicle: " + bookingStatus.getVehicleRegistrationNumber()
                                    + " successful");

                            log.info("booking Vehicle Success vehicleRegistrationNumber: {}, bookingId: {}",
                                    vehicleBookInput.getVehicleRegistrationNumber(),
                                    vehicleBookResponse.getBookingId());
                        }
                    }

                } else {
                    log.info("wrong start and endDuration for vehicle: {}", vehicleBookInput.getVehicleRegistrationNumber());
                    vehicleBookResponse.setMessage("Please Check start and endDuration");
                }
            } else {
                log.info("Vehicle Not available for Booking: {}", vehicleBookInput.getVehicleRegistrationNumber());
                vehicleBookResponse.setMessage("vehicle Not Available for Booking");
            }
        } catch (Exception exception) {
            log.error("error occurred while booking vehicle: {}", exception.getMessage());
            vehicleBookResponse.setMessage("Unknown Error Occurred");
        }

        return vehicleBookResponse;
    }

    public BookingDetailResponse getBookingDetails(String bookingId) {
        Optional<BookingStatus> bookingStatusOptional =
                bookingStatusRepository.findById(bookingId);
        BookingStatus bookingStatus = bookingStatusOptional.orElse(null);

        BookingDetailResponse bookingDetailResponse = new BookingDetailResponse();
        bookingDetailResponse.setSuccess(false);

        if(null != bookingStatus){
            bookingDetailResponse.setSuccess(true);
            bookingDetailResponse.setBookingStatus(bookingStatus);
            bookingDetailResponse.setMessage("Booking Found for BookingId " + bookingId);
        }
        else{
            bookingDetailResponse.setMessage("No Booking Found with BookingId " + bookingId);
        }
        return bookingDetailResponse;
    }

    public BookingCancelResponse bookingCancel(BookingCancelDto bookingCancelDto) {
        BookingCancelResponse bookingCancelResponse = new BookingCancelResponse();
        bookingCancelResponse.setSuccess(false);

        String bookingId = bookingCancelDto.getBookingId();
        Optional<BookingStatus> bookingStatusOptional = bookingStatusRepository.findById(bookingId);
        if (bookingStatusOptional.isPresent()) {
            BookingStatus bookingStatus = bookingStatusOptional.get();
            if(bookingStatus.isRideCompleted()){
                bookingCancelResponse.setMessage("Ride for Booking Already Completed");
            }
            else if (bookingStatus.getBookingStatus().equalsIgnoreCase("booked")) {
                bookingStatus.setBookingStatus("cancelled");
                bookingStatus.setRideCompleted(true);
                bookingStatusRepository.save(bookingStatus);

                bookingCancelResponse.setSuccess(true);
                bookingCancelResponse.setMessage("Vehicle Book: " + bookingStatus.getBookingId() +
                        " successfuly cancelled");
            }
        }
        return bookingCancelResponse;
    }

    public RideCompleteResponse rideComplete(String bookingId){
        RideCompleteResponse rideCompleteResponse = new RideCompleteResponse();
        rideCompleteResponse.setSuccess(false);

        Optional<BookingStatus> bookingStatusOptional = bookingStatusRepository.findById(bookingId);
        if(bookingStatusOptional.isPresent()){
            BookingStatus bookingStatus = bookingStatusOptional.get();
            bookingStatus.setRideCompleted(true);
            bookingStatusRepository.save(bookingStatus);
            rideCompleteResponse.setSuccess(true);
            rideCompleteResponse.setMessage("Ride Completed for vehicle: "+
                    bookingStatus.getVehicleRegistrationNumber());
        }
        else{
            rideCompleteResponse.setMessage("bookingId not Found");
        }
        return rideCompleteResponse;
    }

    public VehicleSlotsResponse getVehicleSlots(String vehicleRegistrationNumber){
        VehicleSlotsResponse vehicleSlotsResponse = new VehicleSlotsResponse();
        vehicleSlotsResponse.setSuccess(true);

        List<BookedSlot> bookedSlotList = new ArrayList<>();

        List<BookingStatus> bookingStatusList =
        bookingStatusRepository.findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted(
                vehicleRegistrationNumber,"booked",false);

        for(BookingStatus bookingStatus: bookingStatusList){
            BookedSlot bookedSlot = new BookedSlot();
            bookedSlot.setStartTime(bookingStatus.getStartTime());
            bookedSlot.setEndTime(bookingStatus.getEndTime());
            bookedSlotList.add(bookedSlot);
        }
        vehicleSlotsResponse.setVehicleRegistrationNumber(vehicleRegistrationNumber);
        vehicleSlotsResponse.setBookedSlotList(bookedSlotList);
        return vehicleSlotsResponse;

    }

    public List<BookingStatus> getAllUserBookings(){
        String email = httpServletRequest.getHeader("email");
        return bookingStatusRepository.findAllByBookedUserEmail(email);
    }

    private VehicleInformationEntity getVehicleInformation(String vehcileRegistrationNumber) {

        Optional<VehicleInformationEntity> vehicleInformationOptional =
                vehicleInformationRepository.findById(vehcileRegistrationNumber);
        return vehicleInformationOptional.orElse(null);
    }

}