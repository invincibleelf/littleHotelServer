package com.littlehotel.littleHotelServer.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO to manage Hotel Information
 * 
 * @author Sharad Shrestha
 *
 */
public class HotelDTO {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String code;

	@Valid
	@NotNull
	private AddressDTO address;

	// TODO Add Regex validation for mobile phone
	@NotNull
	private Integer phoneNumber;

	@NotNull
	@Email
	private String email;

	public HotelDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
