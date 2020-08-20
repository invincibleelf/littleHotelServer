package com.littlehotel.littleHotelServer.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping(value = "/api/users")
@Validated
public class UserController extends GenericRestController<UserService, ApplicationUserDTO, ApplicationUser> {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	private ModelMapper mapper;

	/** Constructor injection of {@link ModelMapper} mapper to add additional mapping to skip fields
	 * @param mapper
	 */
	public  UserController(ModelMapper mapper) {
		this.mapper = mapper;
		
		TypeMap<ApplicationUser, ApplicationUserDTO> typemap = this.mapper.createTypeMap(ApplicationUser.class, ApplicationUserDTO.class);
		
		//Skip setting password field which is set to null
		typemap.addMappings(m -> m.skip(ApplicationUserDTO::setPassword));
		
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> all() {
		logger.info("Request to get all user");
		return super.all();

	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		logger.info("Request to get all user");
		return super.get(id);

	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws Exception {
		logger.info("Request to create user");
		return super.create(applicationUserDTO);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
			@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws Exception {
		logger.info("Request to update user with id = " + id);

		return ResponseEntity.ok().body(service.update(id, applicationUserDTO));
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
		service.delete(id);
		return ResponseEntity.ok().body(new MessageResponse("User Deleted Successfully"));
	}

	@PostMapping(value = "/change-password", produces = "application/json")
	public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDTO passwordDTO, Principal principal)
			throws Exception {
		logger.info("Request change password for user " + principal.getName());

		((UserService) service).changePassword(passwordDTO, principal.getName());

		return ResponseEntity.ok().body(new MessageResponse("Password changed successfully"));

	}

}
