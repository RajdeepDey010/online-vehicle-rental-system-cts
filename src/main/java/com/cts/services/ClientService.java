package com.cts.services;

<<<<<<< HEAD
import com.cts.config.ApplicationProperties;
import com.cts.dto.BookingCancelDto;
import com.cts.entitiy.BookingStatus;
import com.cts.entitiy.User;
import com.cts.entitiy.VehicleInformationEntity;
import com.cts.model.*;
import com.cts.repository.BookingStatusRepository;
import com.cts.repository.UserRepository;
import com.cts.repository.VehicleInformationRepository;
=======
import com.cts.repository.BookingStatusRepository;
import com.cts.repository.VehicleInformationRepository;
import com.cts.config.ApplicationProperties;
import com.cts.dto.BookingCancelDto;
import com.cts.entities.BookingStatus;
import com.cts.entities.VehicleInformationEntity;
import com.cts.model.*;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1

@Slf4j
@Service
public class ClientService {

    private VehicleInformationRepository vehicleInformationRepository;
    private BookingStatusRepository bookingStatusRepository;
    private ApplicationProperties applicationProperties;
<<<<<<< HEAD
    private UserRepository userRepository;
    private HttpServletRequest httpServletRequest;


=======
    private HttpServletRequest httpServletRequest;

>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    @Autowired
    public ClientService(VehicleInformationRepository vehicleInformationRepository,
                         BookingStatusRepository bookingStatusRepository,
                         ApplicationProperties applicationProperties,
<<<<<<< HEAD
                         UserRepository userRepository, HttpServletRequest httpServletRequest) {
        this.vehicleInformationRepository = vehicleInformationRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
=======
                         HttpServletRequest httpServletRequest) {
        this.vehicleInformationRepository = vehicleInformationRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.applicationProperties = applicationProperties;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        this.httpServletRequest = httpServletRequest;
    }

    public VehicleAvailableResponse searchVehicle(VehicleSearch vehicleSearch) {

<<<<<<< HEAD
        VehicleAvailableResponse vehicleAvailableResponse = new VehicleAvailableResponse();
        vehicleAvailableResponse.setVehicleAvailableInCities(new ArrayList<>());
        List<VehicleInformation> vehicleInformationList = new ArrayList<>();

        Iterable<VehicleInformationEntity> vehicleInformationEntityList =
                vehicleInformationRepository.findAll();

        for (VehicleInformationEntity vehicleAvailability : vehicleInformationEntityList) {

            String city = vehicleAvailability.getCity();
            if (!vehicleAvailableResponse.getVehicleAvailableInCities().contains(city)) {
                vehicleAvailableResponse.getVehicleAvailableInCities().add(vehicleAvailability.getCity());
            }

            if (!vehicleAvailability.getCity().equalsIgnoreCase(vehicleSearch.getCity().toLowerCase(Locale.ROOT))) {
                continue;
            }
            VehicleInformation vehicleInformationEntity = new VehicleInformation();
=======
        List<VehicleInformation> vehicleInformationList = new ArrayList<>();
        Iterable<VehicleInformationEntity> vehicleInformationEntityList =
                vehicleInformationRepository.findAllByCity(vehicleSearch.getCity());


        for (VehicleInformationEntity vehicleAvailability : vehicleInformationEntityList) {
            VehicleInformation vehicleInformationEntity = new VehicleInformation();

>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            vehicleInformationEntity.setVehicleName(vehicleAvailability.getVehicleName());
            vehicleInformationEntity.setCity(vehicleAvailability.getCity());
            vehicleInformationEntity.setVehicleRegistrationNumber(vehicleAvailability.getVehicleRegistrationNumber());
            vehicleInformationEntity.setCostPerKilometer(vehicleAvailability.getCostPerKiloMeter());
<<<<<<< HEAD
            vehicleInformationList.add(vehicleInformationEntity);
        }


        vehicleAvailableResponse.setVehicleAvailableForCity(vehicleInformationList.size());
        vehicleAvailableResponse.setVehicleInformationList(vehicleInformationList);
        vehicleAvailableResponse.setTotalCitiesAvailable(vehicleAvailableResponse.getVehicleAvailableInCities().size());
        if (vehicleInformationList.size() == 0) {
            vehicleAvailableResponse.setMessage("No Vehicles Found for City");
        } else {
            vehicleAvailableResponse.setMessage("Vehicles Found for City " + vehicleSearch.getCity());
        }
=======

            vehicleInformationList.add(vehicleInformationEntity);
        }
        VehicleAvailableResponse vehicleAvailableResponse = new VehicleAvailableResponse();
        vehicleAvailableResponse.setTotalVehicleAvailable(vehicleInformationList.size());
        vehicleAvailableResponse.setVehicleInformationList(vehicleInformationList);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        return vehicleAvailableResponse;
    }

    public VehicleBookResponse bookVehicle(VehicleBook vehicleBookInput) {

        VehicleBookResponse vehicleBookResponse = new VehicleBookResponse();
<<<<<<< HEAD
        vehicleBookResponse.setSuccess(false);
=======
        vehicleBookResponse.setBookSuccess(false);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1

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
<<<<<<< HEAD
                    if (endTime.getTime() - startTime.getTime() > applicationProperties.getBookingMaxDuration() * 60 * 1000) {
                        log.info("Booking Duration Time Limit Reached. booking not Available for {} minutes",
                                applicationProperties.getBookingMaxDuration());
                        String hours = Double.toString(applicationProperties.getBookingMaxDuration() / 60.0);

                        vehicleBookResponse.setMessage("booking duration limit is for " + hours + " hours");
                    } else {
=======
                    if(endTime.getTime() - startTime.getTime() > applicationProperties.getBookingMaxDuration() * 60 * 1000){
                        log.info("Booking Duration Time Limit Reached. booking not Available for {} minutes",
                                applicationProperties.getBookingMaxDuration());
                        String hours = Double.toString(applicationProperties.getBookingMaxDuration()/60.0);

                        vehicleBookResponse.setMessage("booking duration limit is for " + hours + " hours");
                    }
                    else{
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
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
<<<<<<< HEAD
                            vehicleBookResponse.setMessage("Time Slot Not Available for Vehicle " + vehicleBookInput.getVehicleRegistrationNumber());
                            vehicleBookResponse.setBookedVehicleSlots(this.getVehicleSlots(vehicleBookInput.getVehicleRegistrationNumber()).getBookedSlotList());
=======
                            vehicleBookResponse.setMessage("Time Slot Not Available. Vehicle Already Registered");
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
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


<<<<<<< HEAD
                            vehicleBookResponse.setSuccess(true);
                            vehicleBookResponse.setBookingId(bookingStatus.getBookingId());
                            vehicleBookResponse.setVehicleRegistrationNumber(bookingStatus.getVehicleRegistrationNumber());
=======
                            vehicleBookResponse.setBookSuccess(true);
                            vehicleBookResponse.setBookingId(bookingStatus.getBookingId());
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
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

<<<<<<< HEAD
        if (null != bookingStatus) {
            bookingDetailResponse.setSuccess(true);
            bookingDetailResponse.setBookingStatus(bookingStatus);
            bookingDetailResponse.setMessage("Booking Found for BookingId " + bookingId);
        } else {
=======
        if(null != bookingStatus){
            bookingDetailResponse.setSuccess(true);
            bookingDetailResponse.setBookingStatus(bookingStatus);
            bookingDetailResponse.setMessage("Booking Found for BookingId " + bookingId);
        }
        else{
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            bookingDetailResponse.setMessage("No Booking Found with BookingId " + bookingId);
        }
        return bookingDetailResponse;
    }

    public BookingCancelResponse bookingCancel(BookingCancelDto bookingCancelDto) {
        BookingCancelResponse bookingCancelResponse = new BookingCancelResponse();
        bookingCancelResponse.setSuccess(false);

        String bookingId = bookingCancelDto.getBookingId();
<<<<<<< HEAD

        Optional<BookingStatus> bookingStatusOptional = bookingStatusRepository.findById(bookingId);
        if (bookingStatusOptional.isPresent()) {
            BookingStatus bookingStatus = bookingStatusOptional.get();
            if (bookingStatus.isRideCompleted()) {
                bookingCancelResponse.setMessage("Ride for Booking Already Completed");
            } else if (bookingStatus.getBookingStatus().equalsIgnoreCase("booked")) {
=======
        Optional<BookingStatus> bookingStatusOptional = bookingStatusRepository.findById(bookingId);
        if (bookingStatusOptional.isPresent()) {
            BookingStatus bookingStatus = bookingStatusOptional.get();
            if(bookingStatus.isRideCompleted()){
                bookingCancelResponse.setMessage("Ride for Booking Already Completed");
            }
            else if (bookingStatus.getBookingStatus().equalsIgnoreCase("booked")) {
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
                bookingStatus.setBookingStatus("cancelled");
                bookingStatus.setRideCompleted(true);
                bookingStatusRepository.save(bookingStatus);

                bookingCancelResponse.setSuccess(true);
<<<<<<< HEAD
                bookingCancelResponse.setMessage("BookingId: " + bookingStatus.getBookingId() +
                        " successfuly cancelled");
            }
        }
        else{
            log.info("bookingId: {} not Found for Cancellation", bookingId);
            bookingCancelResponse.setMessage("bookingId " + bookingId + " Not Found");
        }
        return bookingCancelResponse;
    }

    public RideCompleteResponse rideComplete(RideComplete rideComplete) {
        String bookingId = rideComplete.getBookingId();
=======
                bookingCancelResponse.setMessage("Vehicle Book: " + bookingStatus.getBookingId() +
                        " successfuly cancelled");
            }
        }
        return bookingCancelResponse;
    }

    public RideCompleteResponse rideComplete(String bookingId){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        RideCompleteResponse rideCompleteResponse = new RideCompleteResponse();
        rideCompleteResponse.setSuccess(false);

        Optional<BookingStatus> bookingStatusOptional = bookingStatusRepository.findById(bookingId);
<<<<<<< HEAD
        if (bookingStatusOptional.isPresent()) {
            BookingStatus bookingStatus = bookingStatusOptional.get();
            if(bookingStatus.isRideCompleted()){
                rideCompleteResponse.setMessage("Ride Already Completed");
            }
            else if (rideComplete.getDistanceCovered() > 0) {
                bookingStatus.setRideCompleted(true);
                bookingStatus.setTotalDistanceCovered(rideComplete.getDistanceCovered());
                bookingStatusRepository.save(bookingStatus);
                rideCompleteResponse.setSuccess(true);
                rideCompleteResponse.setMessage("Ride Completed for vehicle: " +
                        bookingStatus.getVehicleRegistrationNumber());
            } else {
                rideCompleteResponse.setMessage("distance Covered must be greater than 0");
            }
        } else {
=======
        if(bookingStatusOptional.isPresent()){
            BookingStatus bookingStatus = bookingStatusOptional.get();
            bookingStatus.setRideCompleted(true);
            bookingStatusRepository.save(bookingStatus);
            rideCompleteResponse.setSuccess(true);
            rideCompleteResponse.setMessage("Ride Completed for vehicle: "+
                    bookingStatus.getVehicleRegistrationNumber());
        }
        else{
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            rideCompleteResponse.setMessage("bookingId not Found");
        }
        return rideCompleteResponse;
    }

<<<<<<< HEAD
    public VehicleSlotsResponse getVehicleSlots(String vehicleRegistrationNumber) {
=======
    public VehicleSlotsResponse getVehicleSlots(String vehicleRegistrationNumber){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        VehicleSlotsResponse vehicleSlotsResponse = new VehicleSlotsResponse();
        vehicleSlotsResponse.setSuccess(true);

        List<BookedSlot> bookedSlotList = new ArrayList<>();

        List<BookingStatus> bookingStatusList =
<<<<<<< HEAD
                bookingStatusRepository.findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted(
                        vehicleRegistrationNumber, "booked", false);

        for (BookingStatus bookingStatus : bookingStatusList) {
=======
        bookingStatusRepository.findAllByVehicleRegistrationNumberAndBookingStatusAndRideCompleted(
                vehicleRegistrationNumber,"booked",false);

        for(BookingStatus bookingStatus: bookingStatusList){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
            BookedSlot bookedSlot = new BookedSlot();
            bookedSlot.setStartTime(bookingStatus.getStartTime());
            bookedSlot.setEndTime(bookingStatus.getEndTime());
            bookedSlotList.add(bookedSlot);
        }
        vehicleSlotsResponse.setVehicleRegistrationNumber(vehicleRegistrationNumber);
        vehicleSlotsResponse.setBookedSlotList(bookedSlotList);
<<<<<<< HEAD
        if(bookingStatusList.size() == 0 ){
            vehicleSlotsResponse.setMessage("Vehicle " + vehicleRegistrationNumber + " Slots Not Found");
        }
=======
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        return vehicleSlotsResponse;

    }

<<<<<<< HEAD
    public List<BookingStatus> getAllUserBookings() {
=======
    public List<BookingStatus> getAllUserBookings(){
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
        String email = httpServletRequest.getHeader("email");
        return bookingStatusRepository.findAllByBookedUserEmail(email);
    }

<<<<<<< HEAD
    public UserDetailsResponse getUserDetails() {
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.setSuccess(false);
        String email = httpServletRequest.getHeader("email");

        // check if email exists in database
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // token authorized to fetch user details
            userDetailsResponse.setMessage("User Details Found");
            userDetailsResponse.setSuccess(true);
            userDetailsResponse.setUserDetails(userDetailsEntityBuilder(
                    userOptional.get()));
        } else {
            userDetailsResponse.setMessage("User " + email + " Not Found");
        }
        return userDetailsResponse;
    }

=======
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    private VehicleInformationEntity getVehicleInformation(String vehcileRegistrationNumber) {

        Optional<VehicleInformationEntity> vehicleInformationOptional =
                vehicleInformationRepository.findById(vehcileRegistrationNumber);
        return vehicleInformationOptional.orElse(null);
    }

<<<<<<< HEAD
    private UserDetails userDetailsEntityBuilder(User user) {
        return UserDetails.builder().name(user.getName()).phonenumber(user.getPhonenumber()).
                licensenumber(user.getLicensenumber()).address(user.getAddress())
                .blocked(user.isBlocked()).userType(user.getUserType()).build();
    }

=======
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}