package com.cts.controller;

import com.cts.constants.ApplicationConstants;
import com.cts.dto.BookingCancelDto;
import com.cts.model.*;
import com.cts.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(ApplicationConstants.CLIENTPREFIX)
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(ApplicationConstants.VEHICLESEARCH)
    public ResponseEntity<VehicleAvailableResponse> searchVehicles(@RequestParam String city) {
        VehicleSearch vehicleSearch = new VehicleSearch();
        vehicleSearch.setCity(city.toLowerCase());
        log.info("::searchVehicles searching vehicles for city: {}", city);
        return new ResponseEntity<>(clientService.searchVehicle(vehicleSearch), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.BOOK)
    public ResponseEntity<VehicleBookResponse> bookVehicle(@RequestBody VehicleBook vehicleBook) {
        log.info("::bookVehicle booking Vehicle {}", vehicleBook);
        return new ResponseEntity<>(clientService.bookVehicle(vehicleBook), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.BOOKINGDETAILS)
    public ResponseEntity<?> getBookingDetails(@RequestParam String bookingId) {
        log.info("::getBookingDetails fetching booking Details for {}", bookingId);
        return new ResponseEntity<>(clientService.getBookingDetails(bookingId), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.BOOKINGCANCEL)
    public ResponseEntity<?> cancelBooking(@RequestBody BookingCancelDto bookingCancelDto) {
        log.info("::cancelBooking bookingId: {}", bookingCancelDto.getBookingId());
        return new ResponseEntity<>(clientService.bookingCancel(bookingCancelDto), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.RIDECOMPLETE)
    public ResponseEntity<RideCompleteResponse> rideComplete(@RequestBody RideComplete rideComplete) {
        log.info("::rideComplete bookingId: {}", rideComplete.getBookingId());
        return new ResponseEntity<>(clientService.rideComplete(rideComplete), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.VEHICLESLOTS)
    public ResponseEntity<VehicleSlotsResponse> vehicleAvailableSlots(@RequestParam String vehicleRegistrationNumber) {
        log.info("::vehicleAvailableSlots vehicleRegistrationNumber: {}", vehicleRegistrationNumber);
        return new ResponseEntity<>(clientService.getVehicleSlots(vehicleRegistrationNumber), HttpStatus.OK);

    }

    @GetMapping(ApplicationConstants.GETALLBOOKING)
    public ResponseEntity<?> getAllBooking() {
        log.info("::getAllBooking");
        return new ResponseEntity<>(clientService.getAllUserBookings(), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.USERDETAILS)
    public ResponseEntity<UserDetailsResponse> getUserDetails() {
        log.info("::getUserDetails");
        return new ResponseEntity<>(clientService.getUserDetails(), HttpStatus.OK);
    }

}
