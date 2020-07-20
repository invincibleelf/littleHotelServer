package com.littlehotel.littleHotelServer.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rooms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String number;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RoomStatus.class)
	@JoinColumn(nullable = false)
	private RoomStatus status;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RoomType.class)
	@JoinColumn(nullable = false)
	private RoomType type;

	@ManyToMany(targetEntity = Reservation.class, mappedBy = "rooms")
	private Set<Reservation> reservations;

	public Room() {

	}

	public Room(String name, String number, String description) {
		this.name = name;
		this.number = number;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
