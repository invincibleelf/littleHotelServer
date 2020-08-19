package com.littlehotel.littleHotelServer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "rooms_status")
public class RoomStatus extends BaseEntity{

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private EnumRoomStatus status;

	private String description;

	@OneToMany(targetEntity = Room.class, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Room> rooms;

	public RoomStatus() {

	}

	public RoomStatus(EnumRoomStatus status, String description) {
		this.status = status;
		this.description = description;
	}

	public EnumRoomStatus getStatus() {
		return status;
	}

	public void setStatus(EnumRoomStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
