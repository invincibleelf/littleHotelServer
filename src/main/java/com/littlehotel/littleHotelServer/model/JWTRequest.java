package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.NotEmpty;

/*
 * @author Sharad Shrestha
 * Class to hold username and password for Login authetication request
 * 
 */
public class JWTRequest {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	public JWTRequest() {
	}
	
	public JWTRequest(String username, String password) {
		this.username = username;
		this.password = password;
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
}
