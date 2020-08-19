package com.littlehotel.littleHotelServer.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.littlehotel.littleHotelServer.constants.EnumRoomType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "rooms_type")
public class RoomType extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private EnumRoomType type;

	private String description;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal rate;
	
	@OneToMany(targetEntity = Room.class, mappedBy = "type", cascade = CascadeType.ALL)
	private List<Room> rooms;

	public RoomType() {

	}
	
	public RoomType(EnumRoomType type, String description,BigDecimal rate) {
		this.type = type;
		this.description = description;
		this.rate = rate;
	}

	public EnumRoomType getType() {
		return type;
	}

	public void setType(EnumRoomType type) {
		this.type = type;
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

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}


	


}
