package com.littlehotel.littleHotelServer.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.service.RoomService;
import com.littlehotel.littleHotelServer.utility.Utils;

/**
 * Rest API controller to handle CRUD opertaions for {@link Room},
 * {@link RoomStatus} and {@link RoomType}
 * 
 * @author Sharad Shrestha
 *
 */
@Controller
@RequestMapping("/api")
@Validated
public class RoomController {

	private final static Logger logger = LogManager.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	/**
	 * Method to retrieve all rooms by filter parameter status
	 * 
	 * @param status  the string value of one of the {@link EnumRoomStatus}
	 * @param request the {@link HttpRequest} object to get all parameters
	 * @return ResonseEntity
	 * 
	 */
	@GetMapping(value = "/rooms", produces = "application/json")
	public ResponseEntity<?> all(@RequestParam(name = "status", required = false) String status,
			HttpServletRequest request) {
		logger.info("Request to get rooms by status " + status);

		Map<String, String[]> params = request.getParameterMap();

		List<Room> rooms;

		// TODO Improve This
		if (params.isEmpty()) {
			rooms = roomService.getRooms();
			return ResponseEntity.ok().body(Utils.convertRoomEntityListToDTO(rooms));
		} else if (params.containsKey("status")) {
			rooms = roomService.getRoomsByStatus(status);
			return ResponseEntity.ok().body(Utils.convertRoomEntityListToDTO(rooms));

		} else {
			return ResponseEntity.badRequest()
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Parameter Error " + params.keySet().toString()));
		}

	}

	/**
	 * Method to retrieve room by id
	 * 
	 * @param id
	 * @return ResonseEntity
	 */
	@GetMapping(value = "/rooms/{id}", produces = "application/json")
	public ResponseEntity<?> getRoomById(@PathVariable("id") Long id) {

		logger.info("Request to get room by id = " + id);

		Room room = roomService.getRoomById(id);

		return ResponseEntity.ok().body(Utils.convertRoomEntityToDTO(room));
	}

	/**
	 * Method to create {@link Room} by user with role ADMIN
	 * 
	 * @param roomDTO   the DTO object representing {@link Room} entity
	 * @param principal the {@link ApplicationUser} using application
	 * @return ResonseEntity
	 * @throws Exception when room status or type is not present
	 */
	@PostMapping(value = "/rooms", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createRoom(@Valid @RequestBody RoomDTO roomDTO, Principal principal) throws Exception {

		logger.info("Request to create Room by user = " + principal.getName());

		Room room = roomService.createRoom(roomDTO);

		return ResponseEntity.ok().body(room);
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
	@PutMapping(value = "/rooms/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRoom(@PathVariable("id") Long id, @Valid @RequestBody RoomDTO roomDTO,
			Principal principal) throws Exception {

		logger.info("Request to update Room  for id = " + id + " by user = " + principal.getName());

		Room room = roomService.updateRoom(id, roomDTO);

		return ResponseEntity.ok().body(Utils.convertRoomEntityToDTO(room));

	}

	/**
	 * Method to retrieve all {@link RoomStatus}
	 * 
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(value = "/room-type")
	public ResponseEntity<?> allRoomType() {

		logger.info("Request to get room types");

		List<RoomType> roomTypes = roomService.getRoomTypes();

		return ResponseEntity.ok().body(Utils.convertRoomTypeEntityListToDTO(roomTypes));
	}

	/**
	 * Method to retrieve {@link RoomStatus} by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/room-type/{id}")
	public ResponseEntity<?> getRoomTypeById(@Positive @PathVariable("id") Long id) {

		logger.info("Request to get room type by id = " + id);

		RoomType roomType = roomService.getRoomTypeById(id);

		return ResponseEntity.ok().body(Utils.convertRoomTypeEntityToDTO(roomType));
	}

	/**
	 * Method to create {@link RoomStatus} by user with role ADMIN
	 * 
	 * @param roomTypeDTO
	 * @return {@link ResponseEntity}
	 */
	@PostMapping(value = "/room-type", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createRoomType(@Valid @RequestBody RoomTypeDTO roomTypeDTO) {

		logger.info("Request to create room type");

		RoomType roomType = roomService.createRoomType(roomTypeDTO);

		return ResponseEntity.ok().body(Utils.convertRoomTypeEntityToDTO(roomType));
	}

	@PutMapping(value = "/room-type/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRoomType(@PathVariable("id") Long id, @Valid @RequestBody RoomTypeDTO roomTypeDTO) {

		logger.info("Request to update room type with id = " + id);

		RoomType roomType = roomService.updateRoomType(id, roomTypeDTO);

		return ResponseEntity.ok().body(Utils.convertRoomTypeEntityToDTO(roomType));
	}

	@GetMapping(value = "/room-status", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> allRoomStatus() {
		logger.info("Request to get all room Status");

		List<RoomStatus> roomStatuses = roomService.getRoomStatuses();

		return ResponseEntity.ok().body(Utils.convertRoomStatusEntityListToDTO(roomStatuses));
	}

	@GetMapping(value = "/room-status/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getRoomStatusById(@Positive @PathVariable Long id) {
		logger.info("Request to get room Status by id = " + id);

		RoomStatus roomStatus = roomService.getRoomStatusById(id);
		return ResponseEntity.ok().body(Utils.convertRoomStatusEntityToDTO(roomStatus));
	}

	@PostMapping(value = "/room-status", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createRoomStatus(@Valid @RequestBody RoomStatusDTO roomStatusDTO) {

		logger.info("Request to create room Status");

		RoomStatus roomStatus = roomService.createRoomStatus(roomStatusDTO);

		return ResponseEntity.ok().body(Utils.convertRoomStatusEntityToDTO(roomStatus));
	}

	@PutMapping(value = "/room-status/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRoomStatus(@Positive @PathVariable("id") Long id,
			@Valid @RequestBody RoomStatusDTO roomStatusDTO) {
		logger.info("Request to update room status with id = " + id);
		RoomStatus roomStatus = roomService.updateRoomStatus(id, roomStatusDTO);
		return ResponseEntity.ok().body(Utils.convertRoomStatusEntityToDTO(roomStatus));
	}

}
