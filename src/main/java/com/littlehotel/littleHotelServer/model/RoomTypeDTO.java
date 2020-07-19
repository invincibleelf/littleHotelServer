package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RoomTypeDTO {

	@Positive
	private Long id;

	@NotBlank
	private String type;

	private String description;

	public RoomTypeDTO() {

	}

	public String getType() {
		return type;
	}

	public void setType(String status) {
		this.type = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
