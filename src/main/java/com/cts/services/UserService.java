package com.cts.services;

import com.cts.dto.UserRegisterDto;
import com.cts.model.UserDetailsResponse;
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;

public interface UserService {
    UserValidateResponse validate(String email, String password);

    UserRegisterResponse register(UserRegisterDto userRegisterDto);
}
