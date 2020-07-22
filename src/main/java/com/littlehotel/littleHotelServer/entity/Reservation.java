package com.littlehotel.littleHotelServer.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumBookingStatus;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate bookedDate = LocalDate.now();

	private LocalDate dateFrom;

	private LocalDate dateTo;

	private Integer adult;

	private Integer child;

	@Enumerated(EnumType.STRING)
	private EnumBookingStatus status;

	@ManyToOne(targetEntity = Hotel.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;

	@ManyToMany(targetEntity = Room.class)
	@JoinTable(name = "reservations_rooms", joinColumns = @JoinColumn(name = "reservation_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<Room> rooms;

	@ManyToOne(targetEntity = Guest.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;

	public Reservation() {

	}

	public Reservation(LocalDate dateFrom, LocalDate dateTo, Integer adult, Integer child, EnumBookingStatus status) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.adult = adult;
		this.child = child;
		this.status = status;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public EnumBookingStatus getStatus() {
		return status;
	}

	public void setStatus(EnumBookingStatus status) {
		this.status = status;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Long getId() {
		return id;
	}

}
