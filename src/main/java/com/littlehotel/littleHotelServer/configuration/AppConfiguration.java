package com.littlehotel.littleHotelServer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.littlehotel.littleHotelServer.entity.User;

@Configuration
public class AppConfiguration {

	@Bean
	public User user() {
		return new User();
	}
}
