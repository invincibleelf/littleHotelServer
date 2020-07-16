package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;


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

	@NotBlank
	private String address;

	@NotBlank
	private String suburb;

	@NotBlank
	private String state;

	@NotBlank
	private String country;

	@NotNull
	private Integer postcode;

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
