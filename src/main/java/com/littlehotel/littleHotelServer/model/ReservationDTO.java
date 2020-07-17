package com.littlehotel.littleHotelServer.model;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class ReservationDTO {

	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookedDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private LocalDate DateFrom;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate DateTo;

	@Positive
	private Integer count = 1;

	@NotNull
	@Positive
	private Long hotelId;

	@Valid
	@NotNull
	@JsonProperty(value = "guest")
	private GuestDTO guestDTO;

	private Set<Long> roomIds;

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
		return DateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		DateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return DateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		DateTo = dateTo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public GuestDTO getGuestDTO() {
		return guestDTO;
	}

	public void setGuestDTO(GuestDTO guestDTO) {
		this.guestDTO = guestDTO;
	}

	public Set<Long> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(Set<Long> roomIds) {
		this.roomIds = roomIds;
	}

}
