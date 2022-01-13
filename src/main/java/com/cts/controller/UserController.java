package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entities.User;
import com.cts.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/validate")
	public String validateUser(@RequestBody User user){
		String user1 = userService.validate(user.getEmail(),user.getPassword());
		if(user1!=null) {
			return user1;
		}
		return "Failed";
	}
}
