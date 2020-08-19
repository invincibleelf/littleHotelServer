package com.littlehotel.littleHotelServer.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.model.HotelDTO;
import com.littlehotel.littleHotelServer.model.MessageResponse;
import com.littlehotel.littleHotelServer.service.HotelService;

/**
 * API Controller to handle request related with hotel CRUD operations
 * 
 * @author Sharad Shrestha
 *
 *
 */
@RestController
@RequestMapping(value = "/api/hotels")
@Validated
public class HotelController extends GenericRestController<HotelService, HotelDTO, Hotel> {

	private static final Logger logger = LogManager.getLogger(HotelController.class);

	/**
	 * API to create hotel by user with role ADMIN
	 * 
	 * @param hotelDTO
	 * @param result
	 * @return ResponseEntity
	 * @throws Exception 
	 * @see BindingResult
	 */
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody HotelDTO hotelDTO) throws Exception {

		logger.info("Request to create hotel ");

		return ResponseEntity.ok().body(service.create(hotelDTO));

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
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody HotelDTO hotelDTO) {
		logger.info("Request to update hotel with id = " + id);

		return ResponseEntity.ok().body(service.update(id, hotelDTO));
	}

	/**
	 * API to delete hotel by user with role admin
	 * 
	 * @param id
	 * @return ResponseEntity with MessageResponse
	 */
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		logger.info("Request to delete hotel with id = " + id);
		service.delete(id);
		return ResponseEntity.ok().body(new MessageResponse("Delete method unimplemented"));

	}
}
