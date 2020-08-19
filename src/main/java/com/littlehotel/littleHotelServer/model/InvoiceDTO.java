package com.littlehotel.littleHotelServer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class InvoiceDTO extends BaseDTO {

	@Positive
	@NotNull
	private BigDecimal amount;

	private LocalDateTime invoiceDateTime;
	
	public InvoiceDTO () {
		
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

}
