package com.littlehotel.littleHotelServer.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.model.MessageResponse;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.service.UserService;
import com.littlehotel.littleHotelServer.utility.Utils;

@RestController
@RequestMapping(value = "/api")
@Validated
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	private ModelMapper mapper;
	
	
	/** Constructor injection of {@link ModelMapper} mapper to add additional mapping to skip fields
	 * @param mapper
	 */
	public  UserController(ModelMapper mapper) {
		this.mapper = mapper;
		
		TypeMap<ApplicationUser, ApplicationUserDTO> typemap = this.mapper.createTypeMap(ApplicationUser.class, ApplicationUserDTO.class);
		
		//Skip setting password field which is set to null
		typemap.addMappings(m -> m.skip(ApplicationUserDTO::setPassword));
		
		//Skip setting roles as its needs converting of ApplicationRole to string value
		typemap.addMappings(m -> m.skip(ApplicationUserDTO::setRoles));
		
	}

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
		List<ApplicationUser> users = userService.getUsers();
		return ResponseEntity.ok().body(Utils.convertApplicationUserEntityListToDTO(users, mapper));

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
		ApplicationUser user = userService.getUserById(id);
		return ResponseEntity.ok().body(Utils.convertApplicationUserEntityToDTO(user, mapper));
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
	public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDTO passwordDTO, Principal principal)
			throws Exception {
		logger.info("Request change password for user " + principal.getName());
		
		userService.changePassword(passwordDTO, principal.getName());
		
		return ResponseEntity.ok().body(new MessageResponse("Password changed successfully"));

	}

}
