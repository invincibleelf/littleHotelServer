package com.littlehotel.littleHotelServer.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.littlehotel.littleHotelServer.constants.EnumRoomType;
import com.littlehotel.littleHotelServer.repository.RoomRepository;

public class RoomTypeDTO {

	@Positive
	private Long id;

	@NotBlank
	private String type;

	private String description;

	@NotNull
	@Positive
	private BigDecimal rate;

	private Long availableRooms;

	public RoomTypeDTO() {

	}

	/**
	 * This constructor is being used to return {@link RoomTypeDTO} from query at
	 * {@link RoomRepository}. Any changes to it needs to be reflected manually
	 * there as well.
	 */
	public RoomTypeDTO(Long id, EnumRoomType type, String description, BigDecimal rate, Long availableRooms) {
		this.id = id;
		this.type = type.name();
		this.description = description;
		this.rate = rate;
		this.availableRooms = availableRooms;
	}

	public String getType() {
		return type;
	}

	public void setType(String status) {
		this.type = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(Long availableRooms) {
		this.availableRooms = availableRooms;
	}

}
