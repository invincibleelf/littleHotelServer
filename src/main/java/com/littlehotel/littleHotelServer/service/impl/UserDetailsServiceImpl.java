package com.littlehotel.littleHotelServer.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.littlehotel.littleHotelServer.constants.EnumAppUserStatus;
import com.littlehotel.littleHotelServer.constants.EnumRole;
import com.littlehotel.littleHotelServer.entity.ApplicationRole;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.entity.VerificationToken;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.repository.RoleRepository;
import com.littlehotel.littleHotelServer.repository.UserRepository;
import com.littlehotel.littleHotelServer.repository.VerificationTokenRepository;

/*
 * @author Sharad Shrestha
 * Implementation Service class to handle application user save 
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private EmailServiceImpl emailService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ApplicationUser user = userRepository.findByStatusAndUsername(EnumAppUserStatus.VERIFIED, username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with name " + username));

		// Add Roles to the user
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new User(user.getUsername(), user.getPassword(), authorities);
//		}

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
	public ApplicationUser createUser(ApplicationUserDTO applicationUserDTO) {
		ApplicationUser user = new ApplicationUser(applicationUserDTO.getUsername(),
				bcryptEncoder.encode(applicationUserDTO.getPassword()), applicationUserDTO.getMobile());

		user.setStatus(EnumAppUserStatus.CREATED);

		Set<String> userRoles = applicationUserDTO.getRoles();
		Set<ApplicationRole> roles = new HashSet<>();

		if (userRoles == null) {
			ApplicationRole userRole = roleRepository.findByName(EnumRole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

		} else {
			userRoles.forEach(role -> {
				switch (role) {
				// TODO Add cases for other required roles

				default:
					ApplicationRole userRole = roleRepository.findByName(EnumRole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		// Create Verification Token After saving user
		VerificationToken verificationToken = createVerificationToken(user);

		// TODO Use scheduler to Send Email as Jobs and Email Template
		emailService.sendEmailVerificationMessage(user, verificationToken.getToken());

		return user;

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

	public void verifyUser(String token) throws Exception {

		logger.info("Find Verification Token " + token);
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new Exception("Token Not Found"));

		// Check Expiration of Verification Token
		LocalDateTime dateTimeNow = LocalDateTime.now();
		if (verificationToken.getExpirationDate().isAfter(dateTimeNow)) {

			// Change status of Application user
			logger.info("Save Verified Application User");
			ApplicationUser applicationUser = verificationToken.getUser();
			applicationUser.setStatus(EnumAppUserStatus.VERIFIED);
			userRepository.save(applicationUser);

			// Expire Token After Use
			logger.info("Save Expired Token");
			verificationToken.setExpirationDate(LocalDateTime.now().minusDays(2));
			verificationTokenRepository.save(verificationToken);
		} else {
			logger.error(
					"Token " + token + " with expiry " + verificationToken.getExpirationDate() + " has been expired");
			// Throw token expired exception			
			throw new Exception("Token has been expired");
		}

	}

}
