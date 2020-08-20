package com.littlehotel.littleHotelServer.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumPaymentStatus;
import com.littlehotel.littleHotelServer.constants.EnumPaymentType;
import com.littlehotel.littleHotelServer.entity.Payment;
import com.littlehotel.littleHotelServer.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

/**
 * @author Sharad Shrestha
 * 
 * Service to create payment
 * Donot extend Generic service
 *
 */
@Service
public class PaymentService {
	
	private static final Logger logger = LogManager.getLogger(PaymentService.class);

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Value("${stripe.secret.key}")
	private String secretKeyStripe;
	
	public Payment createPayment(String email, int amount, String token, EnumPaymentType paymentType)
			throws StripeException {

		Payment payment = new Payment();

		switch (paymentType) {
		case STRIPE:
			Charge charge = createCharge(email, amount, token);
			payment.setPayId(charge.getId());

			if (charge.getStatus().equalsIgnoreCase("succeeded")) {
				payment.setStatus(EnumPaymentStatus.SUCCESS);
			} else {
				payment.setStatus(EnumPaymentStatus.PENDING);
			}

			payment.setType(EnumPaymentType.STRIPE);

			break;

		// TODO Implementaion for Paypal
		case CARD:
		case PAYPAl:
			payment.setStatus(EnumPaymentStatus.COMPLETE);
			payment.setType(EnumPaymentType.CARD);

			break;
		case CASH:
		
			payment.setStatus(EnumPaymentStatus.COMPLETE);
			payment.setType(EnumPaymentType.CASH);
			break;
		default:
			break;

		}

		logger.info("Request database to save payment");
		return paymentRepository.save(payment);
	}

	public Charge createCharge(String email, int amount, String token) throws StripeException {

		Stripe.apiKey = secretKeyStripe;

		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("amount", amount * 100);
		chargeParams.put("currency", "aud");
		chargeParams.put("description", "Test Payment");
		chargeParams.put("source", token);

		logger.info("Call to stripe API to create payment");
		return Charge.create(chargeParams);

	}


	
}
