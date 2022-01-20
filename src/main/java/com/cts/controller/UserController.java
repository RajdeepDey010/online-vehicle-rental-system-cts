package com.cts.controller;

import com.cts.constants.ApplicationConstants;
import com.cts.dto.UserDto;
<<<<<<< HEAD
import com.cts.dto.UserRegisterDto;
import com.cts.model.AuthorizationResponse;
=======
import com.cts.entities.User;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
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

<<<<<<< HEAD
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
=======
    @Autowired
    UserService userService;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1

    @PostMapping(ApplicationConstants.USERVALIDATE)
    public ResponseEntity<UserValidateResponse> validateUser(@RequestBody UserDto userDto) {
        log.info("::validateUser for user: {}", userDto.getEmail());
        return new ResponseEntity<>(userService.validate(userDto.getEmail(), userDto.getPassword()), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.USERREGISTER)
<<<<<<< HEAD
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        log.info("::registerUser user: {}", userRegisterDto.getEmail());
        return new ResponseEntity<>(userService.register(userRegisterDto), HttpStatus.OK);
    }

    @GetMapping(ApplicationConstants.VALIDATETOKEN)
    public ResponseEntity<?> validateToken(){
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setMessage("Bearer Token Valid");
        authorizationResponse.setSuccess(true);
        return new ResponseEntity<>(authorizationResponse, HttpStatus.OK);
=======
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody User user) {
        log.info("::registerUser user: {}", user.getEmail());
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }
}
