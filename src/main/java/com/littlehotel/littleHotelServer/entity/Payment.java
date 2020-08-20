package com.littlehotel.littleHotelServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.littlehotel.littleHotelServer.constants.EnumPaymentStatus;
import com.littlehotel.littleHotelServer.constants.EnumPaymentType;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

	@OneToOne(mappedBy = "payment")
	private Invoice invoice;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumPaymentStatus status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumPaymentType type;
	
	private String payId;

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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	

}
