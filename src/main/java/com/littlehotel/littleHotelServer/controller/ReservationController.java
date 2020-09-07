package com.littlehotel.littleHotelServer.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.service.ReservationService;

/**
 * API Controller to handle request related with reservations
 * 
 * @author Sharad Shrestha
 *
 */
@RestController
@RequestMapping(value = "/api/reservations")
@Validated
public class ReservationController extends GenericRestController<ReservationService, ReservationDTO, Reservation>{

	private final static Logger logger = LogManager.getLogger(ReservationController.class);

	/**
	 * Method to get all reservations by user with role ADMIN
	 * 
	 * @return {@link ResponseEntity}
	 */
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> all() {
		logger.info("Request to retrieve all reservations");
		return super.all();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		
		logger.info("Request to retrieve reservation by id = " + id);
		
		return super.get(id);

	}

	/**
	 * Method to create Reservation as well as guest details for booking
	 * 
	 * @param reservationDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseEntity<?> create(@Valid @RequestBody ReservationDTO reservationDTO) throws Exception {
		logger.info("Request to create reservation");
		
		return ResponseEntity.ok().body(service.create(reservationDTO));

	}

	@Override
	public ResponseEntity<?> update(@PathVariable("id") Long id,
			@Valid @RequestBody ReservationDTO reservationDTO) throws Exception {
		logger.info("Request to update reservation with id = " + id);

		return ResponseEntity.ok().body(service.update(id, reservationDTO));

	}

	@GetMapping(value = "/check-rates")
	public ResponseEntity<?> getAvailableRoomRates(
			@RequestParam("dateFrom") @DateTimeFormat(iso = ISO.DATE) LocalDate dateFrom,
			@RequestParam("dateTo") @DateTimeFormat(iso = ISO.DATE) LocalDate dateTo,
			@RequestParam("hotelId") Long hotelId, HttpServletRequest request) {

		List<RoomTypeDTO> availableRoomTypeDTOs = ((ReservationService) service).getAvailableRoomTypesAndRoomCount(dateFrom, dateTo,
				hotelId);

		return ResponseEntity.ok().body(availableRoomTypeDTOs);
	}

}
