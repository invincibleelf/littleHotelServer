package com.littlehotel.littleHotelServer.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApplicationUserDTO {
	
	private Long id;

	@Email
	@NotEmpty
	private String username;
	
	@NotEmpty
	@Pattern(regexp = "^(?=.*[0-9])(?=.[a-z]).{6,32}")
	private String password;
	
	private Set<String> roles;
	
	// TODO Add regex validation for mobile phone	
	@NotNull
	private Integer mobile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	
}
