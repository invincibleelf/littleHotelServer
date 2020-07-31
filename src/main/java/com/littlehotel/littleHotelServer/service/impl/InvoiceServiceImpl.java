package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.Invoice;
import com.littlehotel.littleHotelServer.model.InvoiceDTO;
import com.littlehotel.littleHotelServer.repository.InvoiceRepository;
import com.littlehotel.littleHotelServer.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> getInvoices() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.getOne(id);
	}
	
	public Invoice createInvoice(InvoiceDTO invoiceDTO) {
		
		
		return null;
	}

}
