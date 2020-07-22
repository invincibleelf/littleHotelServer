package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.littlehotel.littleHotelServer.constants.EnumPaymentStatus;
import com.littlehotel.littleHotelServer.constants.EnumPaymentType;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "payment")
	private Invoice invoice;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumPaymentStatus status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumPaymentType type;
	
	private Long payId;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public EnumPaymentStatus getStatus() {
		return status;
	}

	public void setStatus(EnumPaymentStatus status) {
		this.status = status;
	}

	public EnumPaymentType getType() {
		return type;
	}

	public void setType(EnumPaymentType type) {
		this.type = type;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Long getId() {
		return id;
	}
	

}
