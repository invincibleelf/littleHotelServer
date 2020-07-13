package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumAppUserStatus;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.repository.UserRepository;
import com.littlehotel.littleHotelServer.service.UserService;
import com.littlehotel.littleHotelServer.utility.PasswordMismatchException;

/*
 * @author Sharad Shrestha
 * Implementation Service class to handle application user save 
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public List<ApplicationUser> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public ApplicationUser getUserById(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public void deleteUserById(Long id) {
		ApplicationUser user = userRepository.findById(id).get();
		user.setStatus(EnumAppUserStatus.DELETED);
		userRepository.save(user);
	}

	@Override
	public Boolean checkUserExists(String username) {
		return userRepository.existsByUsername(username);

	}

	@Override
	public void changePassword(PasswordDTO passwordDTO, String username) throws PasswordMismatchException, Exception {
		ApplicationUser applicationUser = userRepository.findByStatusAndUsername(EnumAppUserStatus.VERIFIED, username)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't Exists"));

		if (bcryptEncoder.matches(passwordDTO.getOldPassword(), applicationUser.getPassword())) {
			applicationUser.setPassword(bcryptEncoder.encode(passwordDTO.getNewPassword()));
			userRepository.save(applicationUser);
		} else {
			throw new PasswordMismatchException("Old Password doesn't match",passwordDTO.getOldPassword());
		}

	}

}
