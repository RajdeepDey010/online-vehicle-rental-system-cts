package com.cts;

import com.cts.port.YourApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineVehicleRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineVehicleRentalApplication.class, args);
        SpringApplication.run(YourApplication.class, args);
    }
}




