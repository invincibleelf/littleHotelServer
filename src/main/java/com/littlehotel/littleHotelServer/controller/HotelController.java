package com.littlehotel.littleHotelServer.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.model.HotelDTO;
import com.littlehotel.littleHotelServer.service.impl.HotelServiceImpl;
import com.littlehotel.littleHotelServer.utility.Utils;

/**
 * API Controller to handle request related with hotel CRUD operations
 * 
 * @author Sharad Shrestha
 *
 *
 */
@RestController
@RequestMapping(value = "/api")
@Validated
public class HotelController {

	private static final Logger logger = LogManager.getLogger(HotelController.class);

	@Autowired
	private HotelServiceImpl hotelService;

	/**
	 * API to retrieve list of all hotel informations
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/hotels")
	public ResponseEntity<?> all() {
		logger.info("Request to get list of hotels");
		
		List<Hotel> hotels = hotelService.getAllHotels();
		List<HotelDTO> hotelDTOs = Utils.ConvertHotelEntityListToHotelDTOList(hotels);
		
		return ResponseEntity.ok().body(hotelDTOs);
	}

	/**
	 * API to retrieve hotel information
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/hotels/{id}")
	public ResponseEntity<?> get(@Min(5) @PathVariable("id") Long id) throws Exception {
		logger.info("Request to get hotel with id " + id);
		
		Hotel hotel = hotelService.getHotelById(id);
		HotelDTO hotelDTO = Utils.ConvertHotelEntityToHotelDTO(hotel);
		
		return ResponseEntity.ok().body(hotelDTO);
	}

	/**
	 * API to create hotel by user with role ADMIN
	 * 
	 * @param hotelDTO
	 * @param result
	 * @return ResponseEntity
	 * @see BindingResult
	 */
	@PostMapping(value = "/hotels", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody HotelDTO hotelDTO) {

		logger.info("Request to create hotel ");
		
		Hotel hotel = hotelService.createHotel(hotelDTO);
		
		return ResponseEntity.ok().body(Utils.ConvertHotelEntityToHotelDTO(hotel));

	}

	/**
	 * API to update hotel information
	 * 
	 * @param id       a hotel id
	 * @param hotelDTO
	 * @param result
	 * @return ResponseEntity
	 * @see BindingResult
	 */
	@PutMapping(value = "/hotels/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody HotelDTO hotelDTO) {
		logger.info("Request to update hotel with id = " + id);
		
		Hotel hotel = hotelService.updateHotel(id, hotelDTO);
		
		return ResponseEntity.ok().body(Utils.ConvertHotelEntityToHotelDTO(hotel));
	}

	/**
	 * API to delete hotel by user with role admin
	 * 
	 * @param id
	 * @return ResponseEntity with MessageResponse
	 */
	@DeleteMapping(value = "/hotels/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		logger.info("Request to delete hotel with id = " + id);
		return null;

	}
}
