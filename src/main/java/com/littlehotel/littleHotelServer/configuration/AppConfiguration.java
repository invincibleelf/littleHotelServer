package com.littlehotel.littleHotelServer.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
