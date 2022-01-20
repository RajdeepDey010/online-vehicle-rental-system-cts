package com.cts.services;

<<<<<<< HEAD
import com.cts.dto.UserRegisterDto;
import com.cts.model.UserDetailsResponse;
=======
import com.cts.entities.User;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import com.cts.model.UserRegisterResponse;
import com.cts.model.UserValidateResponse;

public interface UserService {
    UserValidateResponse validate(String email, String password);
<<<<<<< HEAD

    UserRegisterResponse register(UserRegisterDto userRegisterDto);
=======
    UserRegisterResponse register(User user);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}
