package com.littlehotel.littleHotelServer.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

	private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("littlehotelserver@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);

		logger.info("Sending Email to " + to);
		emailSender.send(message);
	}
}
