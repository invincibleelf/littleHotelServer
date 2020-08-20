package com.littlehotel.littleHotelServer.controller;

import java.net.http.HttpRequest;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.ErrorResponse;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.service.RoomService;

/**
 * Rest API controller to handle CRUD opertaions for {@link Room},
 * {@link RoomStatus} and {@link RoomType}
 * 
 * @author Sharad Shrestha
 *
 */
@Controller
@RequestMapping("/api/rooms")
@Validated
public class RoomController extends GenericRestController<RoomService, RoomDTO, Room> {

	private final static Logger logger = LogManager.getLogger(RoomController.class);

	/**
	 * Method to retrieve all rooms by filter parameter status
	 * 
	 * @param status  the string value of one of the {@link EnumRoomStatus}
	 * @param request the {@link HttpRequest} object to get all parameters
	 * @return ResonseEntity
	 * 
	 */
	@GetMapping( produces = "application/json")
	public ResponseEntity<?> all(@RequestParam(name = "status", required = false) String status,
			HttpServletRequest request) {
		logger.info("Request to get rooms by status " + status);

		Map<String, String[]> params = request.getParameterMap();

		// TODO Improve This
		if (params.isEmpty()) {
			return ResponseEntity.ok().body(service.all());
		} else if (params.containsKey("status")) {
			return ResponseEntity.ok().body(((RoomService) service).allByStatus(status));

		} else {
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Parameter Error " + params.keySet().toString()));
		}

	}

	/**
	 * Method to create {@link Room} by user with role ADMIN
	 * 
	 * @param roomDTO   the DTO object representing {@link Room} entity
	 * @param principal the {@link ApplicationUser} using application
	 * @return ResonseEntity
	 * @throws Exception when room status or type is not present
	 */
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody RoomDTO roomDTO) throws Exception {
		
		logger.info("Create Room");
		
		return ResponseEntity.ok().body(service.create(roomDTO));
	}

	/**
	 * Method to update {@link Room} using id and {@link RoomDTO} by user with role
	 * ADMIN
	 * 
	 * @param id        the id od the room
	 * @param roomDTO   the DTO for {@link Room} entity
	 * @param principal
	 * @return {@link ResponseEntity}
	 * @throws Exception when room status or type is not present
	 */
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody RoomDTO roomDTO) throws Exception {

		logger.info("Request to update Room  for id = " + id );

		return ResponseEntity.ok().body(service.update(id, roomDTO));

	}

}
