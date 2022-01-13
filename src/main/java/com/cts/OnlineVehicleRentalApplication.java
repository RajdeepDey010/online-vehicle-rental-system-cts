package com.cts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.cts.Repository.UserRepository;
import com.cts.entities.User;

@SpringBootApplication
public class OnlineVehicleRentalApplication{
	public static void main(String[] args) {
		SpringApplication.run(OnlineVehicleRentalApplication.class, args);
	}
}

@Component
class DemoCommandLiner implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User();
		u1.setAddress("kakinada");
		u1.setEmail("vamsi@gmail.com");
		u1.setLicenseno("vamsi123");
		u1.setName("vamsi");
		u1.setPassword("vamsi@123");
		u1.setPhno("9999999999");
		
		userRepo.save(u1);
		
		User u2 = new User();
		u2.setAddress("vizag");
		u2.setEmail("pavan@gmail.com");
		u2.setLicenseno("pavan123");
		u2.setName("pavan");
		u2.setPassword("pavan@123");
		u2.setPhno("8888888888");
		
		userRepo.save(u2);
		
		User u3 = new User();
		u3.setAddress("hyderabad");
		u3.setEmail("manoj@gmail.com");
		u3.setLicenseno("manoj123");
		u3.setName("manoj");
		u3.setPassword("manoj@123");
		u3.setPhno("7777777777");
		
		userRepo.save(u3);
		
		
	}
	
}




