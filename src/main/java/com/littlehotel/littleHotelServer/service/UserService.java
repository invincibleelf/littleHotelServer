package com.littlehotel.littleHotelServer.service;

import java.util.List;
import com.littlehotel.littleHotelServer.entity.User;

public interface UserService {

	public User createUser(User user);

	public List<User> getUsers();
	
	public User getUserById(Long id);
	
	public void deleteUserById(Long id);

}
