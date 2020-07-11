package com.littlehotel.littleHotelServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.service.impl.EmailServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class MainController {
	
	@Autowired
	private EmailServiceImpl emailServer;

	@GetMapping(value = "/helloUser")
	@PreAuthorize("hasRole('USER')")
	public String helloUser() {
		return "Email Sent";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/helloAdmin")
	public String helloAdmin() {
		return "Hello From Admin";
	}
}
