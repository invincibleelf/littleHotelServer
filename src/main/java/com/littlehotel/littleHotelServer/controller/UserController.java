package com.littlehotel.littleHotelServer.controller;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.model.MessageResponse;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/*
	 * API to retrieve list of all application users informations by current user
	 * with role ADMIN
	 * 
	 * @param none
	 * 
	 * @return ResponseEntity
	 * 
	 */
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> all() throws Exception {

		try {
			logger.info("Get Users");
			return ResponseEntity.ok(userService.getUsers());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}

	}

	/*
	 * API to retrieve Application user information by current user with role ADMIN
	 * 
	 * @param id ApplictionUser id
	 * 
	 * @return ResponseEntity ApplicationUser
	 * 
	 * 
	 */
	@GetMapping(value = "/users/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(userService.getUserById(id));
		} catch (NoSuchElementException e) {
			logger.error("Exception error is " + e.getClass() + e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		} catch (Exception e) {
			logger.error("Exception error is " + e.getClass() + e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}
	}

	/*
	 * API to delete user (change status to DELETE) by current user with role ADMIN
	 * 
	 * @param id ApplictionUser id
	 * 
	 * @return ResponseEntity with MessageResponse
	 * 
	 * @See method in UserService for more details
	 */
	@DeleteMapping(value = "/users/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			logger.info("Request delete user wiht id " + id);
			userService.deleteUserById(id);
			return ResponseEntity.ok().body(new MessageResponse("User Deleted Successfully"));
		} catch (Exception e) {
			logger.error("Exception is " + e.getCause() + e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));

		}

	}

	@PostMapping(value = "/users/change-password", produces = "application/json")
	public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO, Principal principal) {
		try {
			logger.info("Request change password for user " + principal.getName());
			userService.changePassword(passwordDTO, principal.getName());
			return ResponseEntity.ok().body(new MessageResponse("Password changed successfully"));
		} catch (Exception e) {
			logger.error("Exception is " + e.getCause() + e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}

	}

}
