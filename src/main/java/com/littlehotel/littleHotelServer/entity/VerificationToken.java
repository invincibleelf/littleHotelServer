package com.littlehotel.littleHotelServer.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends BaseEntity{

	private String token;
	
	private LocalDateTime expirationDate;
	
	@OneToOne(fetch = FetchType.EAGER, targetEntity = ApplicationUser.class)
	@JoinColumn(nullable = false, name = "user_id")
	private ApplicationUser user;
	
	public VerificationToken() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
}
