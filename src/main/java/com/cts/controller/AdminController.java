package com.cts.controller;

import com.cts.constants.ApplicationConstants;
import com.cts.entitiy.BookingStatus;
import com.cts.entitiy.User;
import com.cts.entitiy.VehicleInformationEntity;
import com.cts.model.VehicleRegisterResponse;
import com.cts.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RequestMapping(ApplicationConstants.ADMINPREFIX)
@RestController
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(ApplicationConstants.REGISTERVEHICLE)
    public ResponseEntity<VehicleRegisterResponse> registerVehicle(@RequestBody VehicleInformationEntity vehicleInformationEntity) {
        log.info("::registerVehicle vehicleInformationEntity: {}", vehicleInformationEntity);
        return new ResponseEntity<>(adminService.registerVechicle(vehicleInformationEntity), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.GETALLVEHICLES)
    public ResponseEntity<List<VehicleInformationEntity>> getAllVehicles() {
        log.info("::getAllVehicles");
        return new ResponseEntity<>(adminService.getListVehicles(), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.GETALLBOOKING)
    public ResponseEntity<List<BookingStatus>> getAllBooking() {
        log.info("::getAllBooking");
        return new ResponseEntity<>(adminService.getAllBooking(), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.GETALLUSERS)
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("::getAllUsers");
        return new ResponseEntity<>(adminService.getAllUserInfo(), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.DELETEVEHICLE)
    public ResponseEntity<List<VehicleInformationEntity>> deleteVehicle(
            @RequestParam String vehicleRegistrationNumber) {
        log.info("::deleteVehicle vehicleRegistrationNumber: {}", vehicleRegistrationNumber);
        return new ResponseEntity<>(adminService.deleteVehicle(vehicleRegistrationNumber), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.BLOCKUSER)
    public ResponseEntity<?> blockUser(@PathVariable String emailAddress) {
        log.info("::blockUser email: {}", emailAddress);
        return new ResponseEntity<>(adminService.blockUser(emailAddress), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.UNBLOCKUSER)
    public ResponseEntity<?> unblockUser(@PathVariable String emailAddress) {
        log.info("::unblockUser email: {}", emailAddress);
        return new ResponseEntity<>(adminService.unBlockUser(emailAddress), HttpStatus.OK);
    }
}
