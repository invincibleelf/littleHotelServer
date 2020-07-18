package com.littlehotel.littleHotelServer.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	private Integer count = 1;

	@Positive
	@NotNull
	private Long hotelId;

	@Valid
	@JsonProperty(value = "guest")
	@NotNull
	private GuestDTO guestDTO;

	@NotEmpty
	private Set<Long> roomIds;

	private List<RoomDTO> rooms;

	public ReservationDTO(Long id, LocalDate dateFrom, LocalDate dateTo, Integer count) {
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.count = count;
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

	public List<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}

}
