package com.littlehotel.littleHotelServer.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationDTO {

	private Long id;

	private LocalDate bookedDate = LocalDate.now();

	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	@NotNull
	private LocalDate dateFrom;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future
	@NotNull
	private LocalDate dateTo;

	@Positive
	@NotNull
	private Integer adult = 1;

	@PositiveOrZero
	private Integer child = 0;

	@Positive
	@NotNull
	private Long hotelId;

	@Valid
	@JsonProperty(value = "guest")
	@NotNull
	private GuestDTO guest;

	private List<Map<String, Long>> roomTypeCountMapList;

	private List<RoomDTO> rooms;

	public ReservationDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public GuestDTO getGuest() {
		return guest;
	}

	public void setGuest(GuestDTO guest) {
		this.guest = guest;
	}

	public List<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}

	public List<Map<String, Long>> getRoomTypeCountMapList() {
		return roomTypeCountMapList;
	}

	public void setRoomTypeCountMapList(List<Map<String, Long>> roomTypeCountMapList) {
		this.roomTypeCountMapList = roomTypeCountMapList;
	}

}
