package com.littlehotel.littleHotelServer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity {

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@OneToOne(targetEntity = Address.class)
	private Address address;

	private Integer phoneNumber;

	private String email;

	@OneToMany(targetEntity = Reservation.class, mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Reservation> reservations;

	@OneToMany(targetEntity = Room.class, mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Room> rooms;

	public Hotel() {

	}

	public Hotel(String name, String code, Integer phoneNumber, String email) {
		this.name = name;
		this.code = code;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Hotel(String name, String code, Integer phoneNumber, String email, Address address) {
		this.name = name;
		this.code = code;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
