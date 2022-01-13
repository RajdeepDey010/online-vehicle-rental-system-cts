package com.cts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Repository.UserRepository;
import com.cts.entities.User;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public String validate(String email, String password) {
		User us = userRepo.findByEmail(email);
		if(us.getEmail().equals(email) && us.getPassword().equals(password)) {
			return us.getName();
		}
		return null;
	}
}
