package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GuestDTO {

	private Long id;

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

	private String address;

	private String suburb;

	@NotBlank
	private String state;

	@NotBlank
	private String country;

	@NotBlank
	private Integer postcode;

	public GuestDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

}