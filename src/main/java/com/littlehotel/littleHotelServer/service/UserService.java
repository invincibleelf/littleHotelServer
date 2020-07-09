package com.littlehotel.littleHotelServer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;

/*
 * @author Sharad Shrestha
 * Interface to handle methods for User Related Operations
 * 
 */
public interface UserService {


	public List<ApplicationUser> getUsers();
	
	public ApplicationUser getUserById(Long id);
	
	public void deleteUserById(Long id);
	
	public Boolean checkUserExists(String username);

}
