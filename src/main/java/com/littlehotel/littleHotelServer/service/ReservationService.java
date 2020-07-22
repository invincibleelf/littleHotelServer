package com.littlehotel.littleHotelServer.service;

import java.time.LocalDate;
import java.util.List;

import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;

public interface ReservationService {

	List<Reservation> getAllReservations();
	
	Reservation getReservationById(Long id);
	
	Reservation createReservation(ReservationDTO reservationDTO) throws Exception;
	
	Reservation updateReservation(Long id, ReservationDTO reservationDTO);

	List<RoomTypeDTO> getAvailableRoomTypesAndRoomCount(LocalDate dateFrom, LocalDate dateTo, Long hotelId);

}
