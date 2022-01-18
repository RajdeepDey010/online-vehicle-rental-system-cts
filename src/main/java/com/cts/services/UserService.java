package com.cts.services;

import com.cts.entities.User;
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;

public interface UserService {
    UserValidateResponse validate(String email, String password);
    UserRegisterResponse register(User user);
}
