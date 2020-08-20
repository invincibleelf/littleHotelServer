package com.littlehotel.littleHotelServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
@Validated
public class InvoiceController {

	@Autowired
	private InvoiceService service;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> all(){
		return ResponseEntity.ok().body(service.all());
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(service.get(id));
	}
	
	
	
}
