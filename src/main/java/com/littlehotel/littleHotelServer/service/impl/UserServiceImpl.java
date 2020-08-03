package com.littlehotel.littleHotelServer.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumAppUserStatus;
import com.littlehotel.littleHotelServer.constants.EnumRole;
import com.littlehotel.littleHotelServer.entity.ApplicationRole;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.entity.VerificationToken;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.repository.RoleRepository;
import com.littlehotel.littleHotelServer.repository.UserRepository;
import com.littlehotel.littleHotelServer.repository.VerificationTokenRepository;
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
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private EmailServiceImpl emailService;

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
			throw new PasswordMismatchException("Old Password doesn't match", passwordDTO.getOldPassword());
		}

	}

	@Override
	public ApplicationUser createUser(ApplicationUserDTO applicationUserDTO) throws Exception {
		ApplicationUser applicationUser = new ApplicationUser(applicationUserDTO.getUsername(),
				bcryptEncoder.encode(applicationUserDTO.getPassword()), applicationUserDTO.getMobile());
		applicationUser.setStatus(EnumAppUserStatus.CREATED);

		Set<String> userRoles = applicationUserDTO.getRoles();
		Set<ApplicationRole> roles = new HashSet<>();

		if (userRoles == null) {
			throw new Exception("Error:Role not found");
		}

		setUserRoles(userRoles, roles);

		applicationUser.setRoles(roles);
		userRepository.save(applicationUser);

		// Create Verification Token After saving user
		VerificationToken verificationToken = createVerificationToken(applicationUser);

		// TODO Use scheduler to Send Email as Jobs and Email Template
		emailService.sendEmailVerificationMessage(applicationUser, verificationToken.getToken());

		return applicationUser;
	}

	
	@Override
	public ApplicationUser updateUser(Long id, ApplicationUserDTO applicationUserDTO) {
		ApplicationUser user = userRepository.getOne(id);
		user.setMobile(applicationUserDTO.getMobile());
		Set<String> userRoles = applicationUserDTO.getRoles();
		Set<ApplicationRole> roles = new HashSet<>();
		
		if(userRoles !=null) {
			setUserRoles(userRoles, roles);
		}
		
		user.setRoles(roles);
		return userRepository.save(user);

	}

	public VerificationToken createVerificationToken(ApplicationUser user) {
		String token = UUID.randomUUID().toString();
		LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setExpirationDate(expirationDate);
		verificationToken.setUser(user);
		return verificationTokenRepository.save(verificationToken);
	}
	
	private void setUserRoles(Set<String> userRoles, Set<ApplicationRole> roles) {
		userRoles.forEach(role -> {
			ApplicationRole userRole = null;
			switch (role) {
			case "ROLE_ADMIN":
				userRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				break;
			case "ROLE_STAFF":
				userRole = roleRepository.findByName(EnumRole.ROLE_STAFF)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				break;
			case "ROLE_USER":
				userRole = roleRepository.findByName(EnumRole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				break;
			}
			roles.add(userRole);
		});
	}


}
