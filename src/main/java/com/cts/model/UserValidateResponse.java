package com.cts.model;


<<<<<<< HEAD
import com.cts.enums.UserType;
=======
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserValidateResponse {
    private String response;
    private String jwtToken;
<<<<<<< HEAD
    private boolean success;
    private UserType userType;
=======
    private boolean loginSuccess;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
}
