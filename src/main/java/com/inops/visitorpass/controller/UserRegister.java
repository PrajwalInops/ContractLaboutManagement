package com.inops.visitorpass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inops.visitorpass.entity.User;
import com.inops.visitorpass.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserRegister {
	
	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user)
	{
		userService.saveUser(user);
		
		return user;
	}
}
