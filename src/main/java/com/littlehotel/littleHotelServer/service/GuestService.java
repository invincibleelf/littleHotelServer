package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.model.GuestDTO;

public interface GuestService {

	List<Guest> getAllGuests();
	
	Guest getGuestById(Long id);
	
	Guest createGuest(GuestDTO guestDTO);
	
	Guest updateGuest(Long id, GuestDTO guestDTO);
}
