package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumCountry;
import com.littlehotel.littleHotelServer.constants.EnumStates;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

	private String address;

	private String suburb;

	@Enumerated(EnumType.STRING)
	private EnumStates state;
	
	@Enumerated(EnumType.STRING)
	private EnumCountry country;
	
	private Integer postcode;
	
	public Address() {
		
	}
	
	public Address (String address, String suburb,Integer postcode) {
		this.address = address;
		this.suburb = suburb;
		this.postcode = postcode;
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
	
	
}
