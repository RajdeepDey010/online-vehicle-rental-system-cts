package com.cts.services;

import com.cts.entitiy.BookingStatus;
import com.cts.entitiy.User;
import com.cts.entitiy.VehicleInformationEntity;
import com.cts.enums.UserType;
import com.cts.model.UserBlockedResponse;
import com.cts.model.VehicleRegisterResponse;
import com.cts.repository.BookingStatusRepository;
import com.cts.repository.UserRepository;
import com.cts.repository.VehicleInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdminService {

    private VehicleInformationRepository vehicleInformationRepository;
    private BookingStatusRepository bookingStatusRepository;
    private UserRepository userRepository;


    @Autowired
    public AdminService(VehicleInformationRepository vehicleInformationRepository,
                        BookingStatusRepository bookingStatusRepository,
                        UserRepository userRepository) {
        this.vehicleInformationRepository = vehicleInformationRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.userRepository = userRepository;
    }

    public List<VehicleInformationEntity> getListVehicles() {
        List<VehicleInformationEntity> vehicleInformationEntityList = new ArrayList<>();
        Iterable<VehicleInformationEntity> vehicleAvailabilityIterable =
                vehicleInformationRepository.findAll();
        vehicleAvailabilityIterable.forEach(vehicleInformationEntityList::add);
        return vehicleInformationEntityList;
    }

    public List<BookingStatus> getAllBooking() {
        List<BookingStatus> bookingStatusList = new ArrayList<>();
        Iterable<BookingStatus> bookingStatusIterable =
                bookingStatusRepository.findAll();
        bookingStatusIterable.forEach(bookingStatusList::add);
        return bookingStatusList;
    }

    public VehicleRegisterResponse registerVechicle(VehicleInformationEntity vehicleInformationEntityInput) {
        VehicleRegisterResponse vehicleRegisterResponse = new VehicleRegisterResponse();
        vehicleRegisterResponse.setSuccess(false);
        String vehicleRegistrationNumber = vehicleInformationEntityInput.getVehicleRegistrationNumber();

        if (vehicleInformationRepository.existsById(vehicleRegistrationNumber)) {
            log.error("::registerVehicle vehicle {} already Registered", vehicleRegistrationNumber);
            vehicleRegisterResponse.setMessage("Vehicle " + vehicleRegistrationNumber + " already exists");
        } else {
            VehicleInformationEntity vehicleInformationEntitySaved =
                    vehicleInformationRepository.save(vehicleInformationEntityInput);
            vehicleRegisterResponse.setVehicleInformationEntity(vehicleInformationEntitySaved);
            vehicleRegisterResponse.setMessage("Vehicle Registration Success for " + vehicleRegistrationNumber);
            vehicleRegisterResponse.setSuccess(true);
            log.info("::registerVehicle vehicleRegistration Success " + vehicleRegistrationNumber);
        }
        return vehicleRegisterResponse;
    }

    public List<VehicleInformationEntity> deleteVehicle(String vehicleRegistrationNumber) {
        if (vehicleInformationRepository.existsById(vehicleRegistrationNumber)) {
            vehicleInformationRepository.deleteById(vehicleRegistrationNumber);
        }
        Iterable<VehicleInformationEntity> vehicleAvailabilityIterable =
                vehicleInformationRepository.findAll();
        List<VehicleInformationEntity> vehicleInformationEntityList = new ArrayList<>();
        vehicleAvailabilityIterable.forEach(vehicleInformationEntityList::add);
        return vehicleInformationEntityList;

    }

    public List<User> getAllUserInfo() {
        List<User> userList = userRepository.findAll();
        userList.forEach((user) -> user.setPassword("####"));
        return userList;
    }

    public UserBlockedResponse blockUser(String emailAddress) {
        User savedUser;

        UserBlockedResponse userBlockedResponse = new UserBlockedResponse();
        userBlockedResponse.setBlocked(false);
        userBlockedResponse.setEmail(emailAddress);

        Optional<User> userOptional =
                userRepository.findByEmail(emailAddress);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getUserType().equals(UserType.ADMIN)) {
                log.info("user {} cannot be blocked as it is ADMIN", user.getEmail());
                userBlockedResponse.setMessage("admin user cannot be blocked");
            } else {
                user.setBlocked(true);
                savedUser = userRepository.save(user);
                userBlockedResponse.setBlocked(savedUser.isBlocked());
                userBlockedResponse.setMessage("user " + savedUser.getEmail() + " block successful");
            }
        }
        return userBlockedResponse;
    }

    public UserBlockedResponse unBlockUser(String emailAddress) {
        User savedUser;

        UserBlockedResponse userBlockedResponse = new UserBlockedResponse();
        userBlockedResponse.setBlocked(false);
        userBlockedResponse.setEmail(emailAddress);

        Optional<User> userOptional =
                userRepository.findByEmail(emailAddress);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlocked(false);
            savedUser = userRepository.save(user);
            userBlockedResponse.setBlocked(savedUser.isBlocked());
        }
        return userBlockedResponse;
    }
}
