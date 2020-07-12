package com.littlehotel.littleHotelServer.utility;

public class PasswordMismatchException extends Exception{

	private static final long serialVersionUID = 123456789;
	
	public PasswordMismatchException(String message) {
		super(message);
	}
	
}
