package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.model.ReservationDTO;

public interface ReservationService {

	List<Reservation> getAllReservations();
	
	Reservation getReservationById(Long id);
	
	Reservation createReservation(ReservationDTO reservationDTO);
	
	Reservation updateReservation(Long id, ReservationDTO reservationDTO);
}
