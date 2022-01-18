package com.cts.controller;

import com.cts.constants.ApplicationConstants;
import com.cts.dto.UserDto;
import com.cts.entities.User;
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;
import com.cts.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(ApplicationConstants.APIPREFIX)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(ApplicationConstants.USERVALIDATE)
    public ResponseEntity<UserValidateResponse> validateUser(@RequestBody UserDto userDto) {
        log.info("::validateUser for user: {}", userDto.getEmail());
        return new ResponseEntity<>(userService.validate(userDto.getEmail(), userDto.getPassword()), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.USERREGISTER)
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody User user) {
        log.info("::registerUser user: {}", user.getEmail());
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }
}
