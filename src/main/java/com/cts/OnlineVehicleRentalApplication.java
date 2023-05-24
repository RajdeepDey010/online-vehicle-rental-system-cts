package com.cts;


import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineVehicleRentalApplication extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OnlineVehicleRentalApplication.class);
    }
    
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
        if (args.length > 0) {
        	System.out.println("Enter a PORT: ");
            int port = sc.nextInt();
            System.setProperty("server.port", String.valueOf(port));
        }
        SpringApplication.run(OnlineVehicleRentalApplication.class, args);
    }
}