package com.littlehotel.littleHotelServer.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("roomStatus")
	private RoomStatusDTO roomStatusDTO;

	@Valid
	@NotNull
	@JsonProperty("roomType")
	private RoomTypeDTO roomTypeDTO;

	public RoomDTO() {
	}

	public RoomDTO(Long id, String name, String number, String description, BigDecimal rate) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.description = description;
		this.rate = rate;
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

	public RoomStatusDTO getRoomStatusDTO() {
		return roomStatusDTO;
	}

	public void setRoomStatusDTO(RoomStatusDTO roomStatusDTO) {
		this.roomStatusDTO = roomStatusDTO;
	}

	public RoomTypeDTO getRoomTypeDTO() {
		return roomTypeDTO;
	}

	public void setRoomTypeDTO(RoomTypeDTO roomTypeDTO) {
		this.roomTypeDTO = roomTypeDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
