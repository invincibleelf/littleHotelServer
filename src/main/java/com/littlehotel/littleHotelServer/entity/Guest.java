package com.littlehotel.littleHotelServer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sharad Shrestha
 *
 */
@Entity
@Table(name = "guests")
public class Guest extends BaseEntity {

	private String firstname;

	private String lastname;

	private Integer mobile;

	@Column(unique = true)
	private String email;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	private Address address;

	@OneToMany(targetEntity = Reservation.class, mappedBy = "guest", cascade = CascadeType.ALL)
	private List<Reservation> reservations;
	
	@OneToMany(targetEntity = Invoice.class,mappedBy = "guest",cascade = CascadeType.ALL)
	private List<Invoice> invoices; 

	public Guest() {

	}

	public Guest(String firstname, String lastname, Integer mobile, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
	}

	public Guest(String firstname, String lastname, Integer mobile, String email, Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

}
