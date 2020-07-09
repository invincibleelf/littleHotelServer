package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.repository.UserRepository;
import com.littlehotel.littleHotelServer.service.UserService;

/*
 * @author Sharad Shrestha
 * Implementation Service class to handle application user save 
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public List<ApplicationUser> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public ApplicationUser getUserById(Long id) {
		
		return userRepository.getOne(id);
	}
	
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Boolean checkUserExists(String username) {
		return userRepository.existsByUsername(username);
	
	}

	


}
