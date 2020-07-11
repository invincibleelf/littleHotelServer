package com.littlehotel.littleHotelServer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.configuration.AuthTokenUtil;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.model.JWTRequest;
import com.littlehotel.littleHotelServer.model.JWTResponse;
import com.littlehotel.littleHotelServer.model.MessageResponse;
import com.littlehotel.littleHotelServer.service.UserService;
import com.littlehotel.littleHotelServer.service.impl.EmailServiceImpl;
import com.littlehotel.littleHotelServer.service.impl.UserDetailsServiceImpl;

/*
 * @author Sharad Shrestha
 * Controller to Handle user authentication, sign-in and register 
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

	private static final Logger logger = LogManager.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenUtil authTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	/*
	 * Return a Response Entity <JWTResponse> containing token after successful
	 * authentication
	 * 
	 * @param authenticationRequest a JWTRequest object containing username and
	 * password
	 * 
	 * @return ResponseEntity with JWTResponse
	 * 
	 * @see JWTRequest, JWTResponse
	 * 
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest)
			throws UsernameNotFoundException, Exception {

		logger.info("Request to authenticate user");

		try {

			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsServiceImpl
					.loadUserByUsername(authenticationRequest.getUsername());

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			final String token = authTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JWTResponse(token, userDetails.getUsername(), roles));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("exception : " + e.getMessage());
		}
	}

	/*
	 * Return a Response Entity <Applicatioon User> containing token after
	 * successful authentication
	 * 
	 * @param applicationUserDTO a ApplicationUserDTO object containing username and
	 * password to be registered
	 * 
	 * @return ResponseEntity with Message Response
	 * 
	 * @see ApplicationUserDTO, ApplicationUser
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody ApplicationUserDTO applicationUserDTO) throws Exception {
		logger.info("Request to Register user");

		// Check if user already exists
		if (userServiceImpl.checkUserExists(applicationUserDTO.getUsername())) {
			logger.info("Check if User Exists");
			return ResponseEntity.badRequest().body(
					new MessageResponse("Error: Username " + applicationUserDTO.getUsername() + " already exists"));
		}

		userDetailsServiceImpl.createUser(applicationUserDTO);

		return ResponseEntity
				.ok(new MessageResponse("User " + applicationUserDTO.getUsername() + " created successfully"));

	}

	/*
	 * Return a Response Entity <Applicatioon User> containing token after
	 * successful authentication
	 * 
	 * @param applicationUserDTO a ApplicationUserDTO object containing username and
	 * password to be registered
	 * 
	 * @return ResponseEntity with MessageResponse
	 * 
	 * @see ApplicationUserDTO, ApplicationUser
	 * 
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public ResponseEntity<?> verifyUser(@Param("token") String token) throws Exception {
		logger.info("Verify user with token "+token);
		try {
		userDetailsServiceImpl.verifyUser(token);
		return ResponseEntity.ok().body(new MessageResponse("User Verified"));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}

	}

	/*
	 * Method to authenticate Application user
	 * 
	 * @param username String
	 * 
	 * @param password String
	 * 
	 * @return void
	 * 
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
