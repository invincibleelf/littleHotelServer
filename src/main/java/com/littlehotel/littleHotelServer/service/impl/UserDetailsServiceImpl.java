package com.littlehotel.littleHotelServer.service.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.repository.UserRepository;

/*
 * @author Sharad Shrestha
 * Implementation Service class to handle application user save 
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ApplicationUser user;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ApplicationUser user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with name " + username);
		} else {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		}

	}

	/*
	 * Method to save application user to database
	 * 
	 * @param applicationUserDTO object containing username and password
	 * 
	 * @return user entity object returned after successful save in database
	 * 
	 * @see ApplicationUser, ApplicationUserDTO
	 * 
	 */
	public ApplicationUser save(ApplicationUserDTO applicationUserDTO) {
		ApplicationUser newUser = new ApplicationUser();
		newUser.setUsername(applicationUserDTO.getUsername());
		newUser.setPassword(bcryptEncoder.encode(applicationUserDTO.getPassword()));
		return userRepository.save(newUser);
	}

}
