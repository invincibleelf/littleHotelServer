package com.littlehotel.littleHotelServer.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.service.impl.ReservationServiceImpl;
import com.littlehotel.littleHotelServer.utility.Utils;

/**
 * API Controller to handle request related with reservations
 * 
 * @author Sharad Shrestha
 *
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class ReservationController {

	private final static Logger logger = LogManager.getLogger(ReservationController.class);

	@Autowired
	private ReservationServiceImpl reservationService;

	/**
	 * Method to get all reservations by user with role ADMIN
	 * 
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(value = "/reservations")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> all() {
		logger.info("Request to retrieve all reservations");
		List<Reservation> reservations = reservationService.getAllReservations();
		return ResponseEntity.ok().body(Utils.convertReservationEnitityListToDTO(reservations));
	}

	@GetMapping(value = "/reservations/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		logger.info("Request to retrieve reservation by id = " + id);
		Reservation reservation = reservationService.getReservationById(id);
		return ResponseEntity.ok().body(Utils.convertReservationEntityToDTO(reservation));
		
	}

	/**
	 * Method to create Reservation as well as guest details for booking
	 * 
	 * @param reservationDTO
	 * @return
	 */
	@PostMapping(value = "/reservations", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
		logger.info("Request to create reservation");

		Reservation reservation = reservationService.createReservation(reservationDTO);

		return ResponseEntity.ok().body(Utils.convertReservationEntityToDTO(reservation));

	}
	
	
	

}
