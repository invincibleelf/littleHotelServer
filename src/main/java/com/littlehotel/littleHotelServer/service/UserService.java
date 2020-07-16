package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.model.PasswordDTO;

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

	void changePassword(PasswordDTO passwordDTO, String username) throws Exception;

}
