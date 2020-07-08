package com.littlehotel.littleHotelServer.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.User;
import com.littlehotel.littleHotelServer.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> index() {
		logger.info("Get Users");
		return userService.getUsers();
	}

	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);

	}

	@GetMapping(value = "/users/{id}", produces = "application/json")
	public User getUser(@PathVariable Long id) {

		return userService.getUserById(id);
	}

	@DeleteMapping(value = "/users/{id}", produces = "application/json")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		
	}

}
