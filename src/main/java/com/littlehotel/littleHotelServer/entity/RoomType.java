package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumRoomType;

@Entity
@Table(name = "rooms_type")
public class RoomType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EnumRoomType type;

	private String description;

	public RoomType() {

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

	public Long getId() {
		return id;
	}
	


}
