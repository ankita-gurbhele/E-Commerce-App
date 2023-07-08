package com.ecommerce.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUsers();
	}
	
//	@PostMapping("/registernewuser")
//	public User registerNewUser(@RequestBody User user) {
//		return userService.registerNewUser(user);
//	}
	
	@PostMapping("/registernewuser")
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}
	
	@GetMapping("/foradmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This url is accesible to admin";
	}
	
	@GetMapping("/foruser")
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "This url is accesible to User";
	}
}
