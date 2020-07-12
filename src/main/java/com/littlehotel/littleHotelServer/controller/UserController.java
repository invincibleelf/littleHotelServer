package com.littlehotel.littleHotelServer.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.model.ErrorResponse;
import com.littlehotel.littleHotelServer.model.MessageResponse;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.service.UserService;
import com.littlehotel.littleHotelServer.utility.Utils;

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
	public ResponseEntity<?> all() {
		logger.info("Get Users");
		return ResponseEntity.ok(userService.getUsers());

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

		return ResponseEntity.ok(userService.getUserById(id));
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
		logger.info("Request delete user wiht id " + id);
		userService.deleteUserById(id);
		return ResponseEntity.ok().body(new MessageResponse("User Deleted Successfully"));
	}

	@PostMapping(value = "/users/change-password", produces = "application/json")
	public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDTO passwordDTO, BindingResult errors,
			Principal principal) throws Exception {
		logger.info("Validate Form");
		if (errors.hasErrors()) {
			ErrorResponse errorResponse = Utils.createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input field",
					errors.getFieldErrors());
			return ResponseEntity.badRequest().body(errorResponse);
		}
		logger.info("Request change password for user " + principal.getName());
		userService.changePassword(passwordDTO, principal.getName());
		return ResponseEntity.ok().body(new MessageResponse("Password changed successfully"));

	}

}
