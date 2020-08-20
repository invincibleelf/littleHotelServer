package com.littlehotel.littleHotelServer.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	private LocalDateTime invoiceDateTime = LocalDateTime.now();

	@ManyToOne(targetEntity = Guest.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;

	@OneToOne(mappedBy = "invoice")
	private Reservation reservation;

	@OneToOne(targetEntity = Payment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private Payment payment;
	
	public Invoice() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getInvoiceDateTime() {
		return invoiceDateTime;
	}

	public void setInvoiceDateTime(LocalDateTime invoiceDateTime) {
		this.invoiceDateTime = invoiceDateTime;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
