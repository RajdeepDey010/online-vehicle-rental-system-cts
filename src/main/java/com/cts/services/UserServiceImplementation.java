package com.cts.services;

import com.cts.repository.UserRepository;
import com.cts.entities.User;
import com.cts.jwt.JwtTokenService;
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public UserValidateResponse validate(String email, String password) {
        String response = "Failed";
        UserValidateResponse userValidateResponse = new UserValidateResponse();
        userValidateResponse.setLoginSuccess(false);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                response = user.getName();
                userValidateResponse.setLoginSuccess(true);
                userValidateResponse.setJwtToken(jwtTokenService.generateToken(user));
                log.info("::validate success for user {}", email);
            }
        }
        if(!userValidateResponse.isLoginSuccess()){
            log.info("::validate failed validation for user: {}", email);
        }
        userValidateResponse.setResponse(response);
        return userValidateResponse;
    }

    @Override
    public UserRegisterResponse register(User user) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setSuccess(false);
        try {
            if (user.getEmail() == null) {
                log.error("Invalid Registration Request Email is required");
                userRegisterResponse.setMessage("Email is Required");
            } else if (!userRepository.existsByEmail(user.getEmail())) {
                user.setBlocked(false);
                userRepository.save(user);
                userRegisterResponse.setSuccess(true);
                log.info("user with email: {}  successfuly registered", user.getEmail());
                userRegisterResponse.setMessage("User with Email: " + user.getEmail() + " Successfuly Registered");
            } else {
                log.error("email {} already present. Registration Not Allowed", user.getEmail());
                userRegisterResponse.setMessage("Email Already Present");
            }
        } catch (Exception exception) {
            log.error("Exception Occurred while Registering New User: {}", exception.getMessage());
            userRegisterResponse.setMessage("Unknown Exception Occurred");
        }
        return userRegisterResponse;
    }
}
