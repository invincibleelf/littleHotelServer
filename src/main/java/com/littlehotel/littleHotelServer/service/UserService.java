package com.littlehotel.littleHotelServer.service;

import java.time.LocalDateTime;
import java.util.HashSet;
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
import com.littlehotel.littleHotelServer.model.ApplicationRoleDTO;
import com.littlehotel.littleHotelServer.model.ApplicationUserDTO;
import com.littlehotel.littleHotelServer.model.PasswordDTO;
import com.littlehotel.littleHotelServer.repository.RoleRepository;
import com.littlehotel.littleHotelServer.repository.UserRepository;
import com.littlehotel.littleHotelServer.repository.VerificationTokenRepository;
import com.littlehotel.littleHotelServer.service.impl.EmailServiceImpl;
import com.littlehotel.littleHotelServer.utility.PasswordMismatchException;

@Service
public class UserService extends GenericService<ApplicationUserDTO, ApplicationUser> {
	
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
	public ApplicationUserDTO create(ApplicationUserDTO dto) throws Exception {
		ApplicationUser applicationUser = mapperUtil.mapModel(dto,super.entityClass);
		applicationUser.setPassword(bcryptEncoder.encode(dto.getPassword()));
		applicationUser.setStatus(EnumAppUserStatus.CREATED);
		
		Set<ApplicationRoleDTO> userRoles = dto.getRoles();
		Set<ApplicationRole> roles = new HashSet<>();

		if (userRoles == null) {
			throw new Exception("Error:Role not found");
		}
		
		setUserRoles(userRoles, roles);

		applicationUser.setRoles(roles);
		repository.save(applicationUser);

		// Create Verification Token After saving user
		VerificationToken verificationToken = createVerificationToken(applicationUser);

		// TODO Use scheduler to Send Email as Jobs and Email Template
		emailService.sendEmailVerificationMessage(applicationUser, verificationToken.getToken());
		
		return mapperUtil.mapModel(applicationUser, super.dtoClass);
		
	}

	@Override
	public ApplicationUserDTO update(Long id, ApplicationUserDTO dto) {
		ApplicationUser applicationUser = repository.getOne(id);
		applicationUser.setMobile(dto.getMobile());
		Set<ApplicationRoleDTO> userRoles = dto.getRoles();
		Set<ApplicationRole> roles = new HashSet<>();
		
		if(userRoles !=null) {
			setUserRoles(userRoles, roles);
		}
		
		applicationUser.setRoles(roles);
		
		return mapperUtil.mapModel(repository.save(applicationUser), dtoClass);
		
	}

	@Override
	public void delete(Long id) {
		ApplicationUser applicationUser = repository.getOne(id);
		applicationUser.setStatus(EnumAppUserStatus.DELETED);
		repository.save(applicationUser);
		
	}
	
	public void changePassword(PasswordDTO passwordDTO, String username) throws PasswordMismatchException, Exception {
		ApplicationUser applicationUser =  userRepository.findByStatusAndUsername(EnumAppUserStatus.VERIFIED, username)
				.orElseThrow(() -> new UsernameNotFoundException("User doesn't Exists"));

		if (bcryptEncoder.matches(passwordDTO.getOldPassword(), applicationUser.getPassword())) {
			applicationUser.setPassword(bcryptEncoder.encode(passwordDTO.getNewPassword()));
			repository.save(applicationUser);
		} else {
			throw new PasswordMismatchException("Old Password doesn't match", passwordDTO.getOldPassword());
		}

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
	
	public Boolean checkUserExists(String username) {
		return ((UserRepository) repository).existsByUsername(username);

	}
	
	
	private void setUserRoles(Set<ApplicationRoleDTO> userRoles, Set<ApplicationRole> roles) {
		userRoles.forEach(role -> {
			ApplicationRole userRole = null;
			switch (role.getName()) {
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
