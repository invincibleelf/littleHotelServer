package com.littlehotel.littleHotelServer.utility;

public class PasswordMismatchException extends Exception {

	private static final long serialVersionUID = 123456789;

	private String rejectedValue;

	public PasswordMismatchException(String message) {
		super(message);
	}

	public PasswordMismatchException(String message, String rejectedValue) {
		super(message);
		this.rejectedValue = rejectedValue;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

}
