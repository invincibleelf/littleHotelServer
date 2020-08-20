package com.littlehotel.littleHotelServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.model.InvoiceDTO;
import com.littlehotel.littleHotelServer.repository.InvoiceRepository;
import com.littlehotel.littleHotelServer.utility.GenericMapperUtil;

/**
 * @author Sharad Shrestha
 * 
 * Invoice service
 * 
 * Donot extend generic service as Invoice are only created after reservations and payments
 *
 */
@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository repository;
	
	@Autowired
	private GenericMapperUtil mapperUtil;
	
	public List<InvoiceDTO> all() {
		
		return mapperUtil.mapList(repository.findAll(), InvoiceDTO.class);
	}
	
	public InvoiceDTO get(Long id) {
		
		return mapperUtil.mapModel(repository.getOne(id), InvoiceDTO.class);
	}
	
}
