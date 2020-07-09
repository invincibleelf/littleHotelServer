package com.littlehotel.littleHotelServer.model;
/*
 * @author Sharad Shrestha
 * Response call to send message
*/
public class MessageResponse {
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
