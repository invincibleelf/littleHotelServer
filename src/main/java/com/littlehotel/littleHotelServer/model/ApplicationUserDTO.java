package com.littlehotel.littleHotelServer.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApplicationUserDTO extends BaseDTO {

	@Email
	@NotEmpty
	private String username;

	@Pattern(regexp = "^(?=.*[0-9])(?=.[a-z]).{6,32}")
	private String password;
	
	private Set<ApplicationRoleDTO> roles;
	
	// TODO Add regex validation for mobile phone	
	@NotNull
	private Integer mobile;
	
	private String status;
	
	public ApplicationUserDTO() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<ApplicationRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<ApplicationRoleDTO> roles) {
		this.roles = roles;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
