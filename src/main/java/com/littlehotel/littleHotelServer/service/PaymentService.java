package com.littlehotel.littleHotelServer.service;

import com.littlehotel.littleHotelServer.constants.EnumPaymentType;
import com.littlehotel.littleHotelServer.entity.Payment;
import com.stripe.exception.StripeException;

public interface PaymentService {

	Payment createPayment(String email, int amount, String token,EnumPaymentType paymentType) throws StripeException;
}
