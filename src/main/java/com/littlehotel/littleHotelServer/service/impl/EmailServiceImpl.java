package com.littlehotel.littleHotelServer.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;

@Component
public class EmailServiceImpl {

	@Value("${spring.mail.username}")
	private String serverEmail;
	
	@Value("${mail.verificationapi}")
	private String verificationApiUrl;

	private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

	@Autowired
	private JavaMailSender emailSender;

	@Async
	public void sendEmailVerificationMessage(ApplicationUser applicationUser, String token) {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(applicationUser.getUsername());
			helper.setSubject("Verify Email");
			// TODO ISSUE EXISTS WHILE TRYING TO SEND :colon in anchor html			
			helper.setText("Dear " + applicationUser.getUsername() + ",<br/>Please verify your email with link <br/> "
					+ this.verificationApiUrl + token,
					true);
			logger.info("Sending Email to " + applicationUser.getUsername());
			emailSender.send(message);
		} catch (MessagingException e) {
			logger.error(e.getMessage());

		}

	}
}
