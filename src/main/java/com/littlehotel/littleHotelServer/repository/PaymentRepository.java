package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}