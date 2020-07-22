package com.littlehotel.littleHotelServer.utility;

public class AvailableRoomLessThanBookedException extends RuntimeException {

	private static final long serialVersionUID = 123456789L;

	private String rejectedType;

	public AvailableRoomLessThanBookedException(String message) {
		super(message);
	}

	public AvailableRoomLessThanBookedException(String message, String rejectedType) {
		super(message);
		this.rejectedType = rejectedType;
	}

	public String getRejectedType() {
		return rejectedType;
	}

	public void setRejectedType(String rejectedType) {
		this.rejectedType = rejectedType;
	}

}
