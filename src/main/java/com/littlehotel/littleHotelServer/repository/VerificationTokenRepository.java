package com.littlehotel.littleHotelServer.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends GenericRepository<VerificationToken> {
	
	public Optional<VerificationToken> findByToken(String token);
}
