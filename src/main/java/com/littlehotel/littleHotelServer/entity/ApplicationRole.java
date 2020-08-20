package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumRole;

@Entity
@Table(name = "roles")
public class ApplicationRole extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	@Column
	private EnumRole name;
	
	public ApplicationRole() {
		
	}
	
	public ApplicationRole(EnumRole name) {
		this.name = name;
	}

	public EnumRole getName() {
		return name;
	}

	public void setName(EnumRole name) {
		this.name = name;
	}
	
	

}
