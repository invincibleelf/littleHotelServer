package com.littlehotel.littleHotelServer.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GuestDTO extends BaseDTO{

	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

	@NotNull
	@Positive
	private Integer mobile;

	@NotNull
	@Email
	private String email;

	@Valid
	@NotNull
	private AddressDTO address;

	public GuestDTO() {
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public AddressDTO getAddress() {
		return address;
	}

}
