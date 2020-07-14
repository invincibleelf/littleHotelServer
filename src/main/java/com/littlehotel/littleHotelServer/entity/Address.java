package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.littlehotel.littleHotelServer.constants.EnumCountry;
import com.littlehotel.littleHotelServer.constants.EnumStates;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address;

	private String suburb;

	@Enumerated(EnumType.STRING)
	private EnumStates state;
	
	@Enumerated(EnumType.STRING)
	private EnumCountry country;
	
	private Integer postcode;

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

	public EnumStates getState() {
		return state;
	}

	public void setState(EnumStates state) {
		this.state = state;
	}

	public EnumCountry getCountry() {
		return country;
	}

	public void setCountry(EnumCountry country) {
		this.country = country;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public Long getId() {
		return id;
	}
	
	
}
