package com.littlehotel.littleHotelServer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.littlehotel.littleHotelServer.constants.EnumRole;
import com.littlehotel.littleHotelServer.entity.ApplicationRole;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.repository.RoleRepository;
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

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ApplicationUser user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with name " + username);
		} else {
			
			// Add Roles to the user
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
			return new User(user.getUsername(), user.getPassword(), authorities);
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
	public ApplicationUser createUser(ApplicationUserDTO applicationUserDTO) {
		ApplicationUser user = new ApplicationUser(applicationUserDTO.getUsername(),
				bcryptEncoder.encode(applicationUserDTO.getPassword()));

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
		return userRepository.save(user);
	}

}
