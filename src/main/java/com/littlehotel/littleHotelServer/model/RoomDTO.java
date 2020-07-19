package com.littlehotel.littleHotelServer.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RoomDTO {

	@Positive
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String number;

	@NotEmpty
	private String description;

	@NotNull
	@Positive
	private BigDecimal rate;

	@NotNull
	@Positive
	private Long hotelId;

	private String hotelName;

	@Valid
	@NotNull
	private RoomStatusDTO roomStatus;

	@Valid
	@NotNull
	private RoomTypeDTO roomType;

	public RoomDTO() {
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public RoomStatusDTO getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatusDTO roomStatus) {
		this.roomStatus = roomStatus;
	}

	public RoomTypeDTO getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypeDTO roomType) {
		this.roomType = roomType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
