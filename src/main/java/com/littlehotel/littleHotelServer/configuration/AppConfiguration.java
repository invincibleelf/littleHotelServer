package com.littlehotel.littleHotelServer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;
/*
 * @author Sharad Shrestha
 * Main Configuration Class for Beans
 */
@Configuration
public class AppConfiguration {

	@Bean
	public ApplicationUser applicationUser() {
		return new ApplicationUser();
	}
}
