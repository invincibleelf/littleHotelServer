package com.littlehotel.littleHotelServer.service;

import java.util.List;
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

}
