package com.littlehotel.littleHotelServer.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class MainController {

	@GetMapping(value = "/auth/test")
	public String helloUser() {
		return "Email Sent";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/helloAdmin")
	public String helloAdmin() {
		return "Hello From Admin";
	}
}
