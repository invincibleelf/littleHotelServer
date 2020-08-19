package com.littlehotel.littleHotelServer.model;

import javax.persistence.Id;

/**
 * @author Sharad Shrestha
 * 
 * Generic DTO
 *
 */
public class BaseDTO {

	@Id
	private Long id;
	
	public BaseDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
