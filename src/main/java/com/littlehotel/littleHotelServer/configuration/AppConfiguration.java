package com.littlehotel.littleHotelServer.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;

/*
 * @author Sharad Shrestha
 * Main Configuration Class for Beans
 */
@Configuration
@EnableAsync
public class AppConfiguration {

	@Bean
	public ApplicationUser applicationUser() {
		return new ApplicationUser();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
