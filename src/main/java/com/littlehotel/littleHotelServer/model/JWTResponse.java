package com.littlehotel.littleHotelServer.model;

import java.io.Serializable;
import java.util.List;

/*
 * @author Sharad Shrestha
 * Class to respond jwt token after successful authentication
 * 
 */
public class JWTResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private final String jwttoken;
	
	private String username;
	
	private List<String> roles;

	public JWTResponse(String jwttoken,String username,List<String> roles) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.roles = roles;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getRoles() {
		return roles;
	}

}
