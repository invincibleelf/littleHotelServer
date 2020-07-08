package com.littlehotel.littleHotelServer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class MainController {

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello From API";
	}
}
