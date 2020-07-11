package com.littlehotel.littleHotelServer.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.littlehotel.littleHotelServer.constants.EnumAppUserStatus;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user")
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	private String password;

	@Column(nullable = false)
	private Integer mobile;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumAppUserStatus status;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = ApplicationRole.class)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<ApplicationRole> roles = new HashSet<>();

	public ApplicationUser() {

	}

	public ApplicationUser(String username, String password, Integer mobile) {
		this.username = username;
		this.password = password;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<ApplicationRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<ApplicationRole> roles) {
		this.roles = roles;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public EnumAppUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumAppUserStatus status) {
		this.status = status;
	}

}
