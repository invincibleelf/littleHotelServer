package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.Guest;

public interface GuestService {

	List<Guest> getAllGuests();
	
	Guest getGuestById(Long id);
	
	Guest createGuest();
	
	Guest updateGuest();
}
