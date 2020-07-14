package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;

@Entity
@Table(name = "rooms_status")
public class RoomStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false )
	private EnumRoomStatus status;

	private String description;
	
	public RoomStatus() {
		
	}

	public Long getId() {
		return id;
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

}
