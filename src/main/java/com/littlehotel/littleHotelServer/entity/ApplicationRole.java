package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumRole;

@Entity
@Table(name = "roles")
public class ApplicationRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column
	private EnumRole name;
	
	public ApplicationRole() {
		
	}
	
	public ApplicationRole(EnumRole name) {
		this.name = name;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRole getName() {
		return name;
	}

	public void setName(EnumRole name) {
		this.name = name;
	}
	
	

}
