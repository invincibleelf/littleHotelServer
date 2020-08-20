package com.littlehotel.littleHotelServer.model;

public class PaymentDTO extends BaseDTO {
	
	private String status;
	
	private String type;
	
	private String payId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
}
