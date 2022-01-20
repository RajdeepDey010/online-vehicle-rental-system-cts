package com.cts.services;

import com.cts.config.ApplicationProperties;
import com.cts.dto.UserRegisterDto;
import com.cts.entitiy.User;
import com.cts.enums.UserType;
import com.cts.jwt.JwtTokenService;
import com.cts.model.UserDetails;
import com.cts.model.UserDetailsResponse;
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;
import com.cts.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtTokenService jwtTokenService;
    private ApplicationProperties applicationProperties;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository,
                                     JwtTokenService jwtTokenService,
                                     ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public UserValidateResponse validate(String email, String password) {
        String response = "Failed";
        UserValidateResponse userValidateResponse = new UserValidateResponse();
        userValidateResponse.setSuccess(false);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                response = user.getName();
                userValidateResponse.setSuccess(true);
                userValidateResponse.setJwtToken(jwtTokenService.generateToken(user));
                userValidateResponse.setUserType(user.getUserType());
                log.info("::validate success for user {}", email);
            }
        }
        if (!userValidateResponse.isSuccess()) {
            log.info("::validate failed validation for user: {}", email);
        }
        userValidateResponse.setResponse(response);
        return userValidateResponse;
    }

    @Override
    public UserRegisterResponse register(UserRegisterDto userRegisterDto) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setSuccess(false);
        try {
            if ((userRegisterDto.getUserType().equals("admin") &&
                    (null == userRegisterDto.getToken() ||
                            !userRegisterDto.getToken().equalsIgnoreCase(applicationProperties.getAdminPassword())))) {
                log.error("Invalid token for admin Registration");
                userRegisterResponse.setMessage("Please user valid admin token for admin registration");
            } else if ((userRegisterDto.getUserType().equalsIgnoreCase("merchant") &&
                    (null == userRegisterDto.getToken() ||
                            !userRegisterDto.getToken().equals(applicationProperties.getMerchantPassword())))) {
                log.error("Invalid token for merchant Registration");
                userRegisterResponse.setMessage("Please user valid admin token for merchant registration");
            } else if (userRegisterDto.getEmail() == null) {
                log.error("Invalid Registration Request Email is required");
                userRegisterResponse.setMessage("Email is Required");
            } else if (!userRepository.existsByEmail(userRegisterDto.getEmail())) {
                User user = userEntityBuilder(userRegisterDto);
                if (user.getUserType() != null) {
                    user = userRepository.save(user);
                    userRegisterResponse.setSuccess(true);
                    log.info("user with email {}  successfuly registered", user.getEmail());
                    userRegisterResponse.setMessage("User with Email: " + user.getEmail() + " Successfuly Registered");
                    userRegisterResponse.setUserType(user.getUserType());
                } else {
                    log.info("userType must be one of admin/merchant/client");
                    userRegisterResponse.setMessage("usertype must be one of admin/merchant/client");
                }
            } else {
                log.error("email {} already present. Registration Not Allowed", userRegisterDto.getEmail());
                userRegisterResponse.setMessage("User with Email " + userRegisterDto.getEmail() + " Already Exists");
            }
        } catch (Exception exception) {
            log.error("Exception Occurred while Registering New User: {}", exception.getMessage());
            userRegisterResponse.setMessage("Unknown Exception Occurred");
        }
        return userRegisterResponse;
    }


    private User userEntityBuilder(UserRegisterDto userRegisterDto) {
        UserType userType = null;
        if (userRegisterDto.getUserType().equalsIgnoreCase("admin")) {
            userType = UserType.ADMIN;
        } else if (userRegisterDto.getUserType().equalsIgnoreCase("client")) {
            userType = UserType.CLIENT;
        } else if (userRegisterDto.getUserType().equalsIgnoreCase("merchant")) {
            userType = UserType.MERCHANT;
        }
        return User.builder().name(userRegisterDto.getName()).email(userRegisterDto.getEmail())
                .phonenumber(userRegisterDto.getPhno()).licensenumber(userRegisterDto.getLicenseno())
                .address(userRegisterDto.getAddress()).password(userRegisterDto.getPassword())
                .blocked(false).userType(userType).build();

    }
}
