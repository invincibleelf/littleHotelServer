package com.littlehotel.littleHotelServer.model;

import java.io.Serializable;

/*
 * @author Sharad Shrestha
 * Class to respond jwt token after successful authentication
 * 
 */
public class JWTResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private final String jwttoken;
	
	private String username;

	public JWTResponse(String jwttoken,String username) {
		this.jwttoken = jwttoken;
		this.username = username;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return username;
	}

}
