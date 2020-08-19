package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RoomStatusDTO extends BaseDTO {

	@NotBlank
	private String status;

	private String description;

	public RoomStatusDTO() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
