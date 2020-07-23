package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.Invoice;

public interface InvoiceService {

	List<Invoice> getInvoices();

	Invoice getInvoiceById(Long id);

}
