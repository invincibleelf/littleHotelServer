package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.constants.EnumRoomType;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.repository.RoomRepository;
import com.littlehotel.littleHotelServer.repository.RoomStatusRepository;
import com.littlehotel.littleHotelServer.repository.RoomTypeRepository;
import com.littlehotel.littleHotelServer.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	private final static Logger logger = LogManager.getLogger(RoomServiceImpl.class);

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomStatusRepository roomStatusRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}

	@Override
	public List<Room> getRoomsByStatus(String status) {

		EnumRoomStatus st = EnumRoomStatus.valueOf(status);
		logger.info("Database call to get rooms by status = " + st);
		return roomRepository.findAllByStatus(st);
	}

	@Override
	public Room getRoomById(Long id) {
		return roomRepository.getOne(id);
	}

	@Override
	public Room createRoom(RoomDTO roomDTO) throws Exception {
		Room room = new Room(roomDTO.getName(), roomDTO.getNumber(), roomDTO.getDescription(), roomDTO.getRate());

		logger.info("Database call to get hotel for id = " + roomDTO.getHotelId());
		Hotel hotel = hotelRepository.getOne(roomDTO.getHotelId());
		room.setHotel(hotel);

		/*
		 * Check if room status id and room type is null. Important as the id in the
		 * DTOs are not validated by Spring validation
		 */
		if (roomDTO.getRoomStatusDTO().getId() == null || roomDTO.getRoomTypeDTO().getId() == null) {
			// TODO Create custom exception to handle this case
			throw new Exception("Field id is null");
		}

		logger.info("Database call to get room status for id = " + roomDTO.getRoomStatusDTO().getId());
		RoomStatus status = roomStatusRepository.getOne(roomDTO.getRoomStatusDTO().getId());
		room.setStatus(status);

		logger.info("Database call to get room type for id = " + roomDTO.getRoomTypeDTO().getId());
		RoomType type = roomTypeRepository.getOne(roomDTO.getRoomTypeDTO().getId());
		room.setType(type);

		logger.info("Database call to save room");
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Long id, @Valid RoomDTO roomDTO) throws Exception {
		Room room = roomRepository.getOne(id);

		room.setName(roomDTO.getName());
		room.setNumber(roomDTO.getNumber());
		room.setDescription(roomDTO.getDescription());
		room.setRate(roomDTO.getRate());

		/*
		 * Check if room status id and room type is null. Important as the id in the
		 * DTOs are not validated by Spring validation
		 */
		if (roomDTO.getRoomStatusDTO().getId() == null || roomDTO.getRoomTypeDTO().getId() == null) {
			// TODO Create custom exception to handle this case
			throw new Exception("Field id is null");
		}

		logger.info("Database call to get room status for id = " + roomDTO.getRoomStatusDTO().getId());
		RoomStatus status = roomStatusRepository.getOne(roomDTO.getRoomStatusDTO().getId());
		room.setStatus(status);

		logger.info("Database call to get room type for id = " + roomDTO.getRoomTypeDTO().getId());
		RoomType type = roomTypeRepository.getOne(roomDTO.getRoomTypeDTO().getId());
		room.setType(type);

		logger.info("Database call to save room");
		return roomRepository.save(room);

	}

	@Override
	public List<RoomStatus> getRoomStatuses() {
		return roomStatusRepository.findAll();
	}

	@Override
	public RoomStatus getRoomStatusById(Long id) {
		return roomStatusRepository.getOne(id);
	}

	@Override
	public RoomStatus createRoomStatus(RoomStatusDTO roomStatusDTO) {
		RoomStatus roomStatus = new RoomStatus(EnumRoomStatus.valueOf(roomStatusDTO.getStatus()),
				roomStatusDTO.getDescription());

		logger.info("Save room status");
		return roomStatusRepository.save(roomStatus);
	}

	@Override
	public RoomStatus updateRoomStatus(Long id, RoomStatusDTO roomStatusDTO) {
		RoomStatus status = roomStatusRepository.getOne(id);
		status.setStatus(EnumRoomStatus.valueOf(roomStatusDTO.getStatus()));
		status.setDescription(roomStatusDTO.getDescription());

		logger.info("Update room status with id = " + id);
		return roomStatusRepository.save(status);
	}

	@Override
	public List<RoomType> getRoomTypes() {
		return roomTypeRepository.findAll();
	}

	@Override
	public RoomType getRoomTypeById(Long id) {
		return roomTypeRepository.getOne(id);
	}

	@Override
	public RoomType createRoomType(RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType(EnumRoomType.valueOf(roomTypeDTO.getType()), roomTypeDTO.getDescription());

		logger.info("Save room status");
		return roomTypeRepository.save(roomType);
	}

	@Override
	public RoomType updateRoomType(Long id, RoomTypeDTO roomTypeDTO) {
		RoomType roomType = roomTypeRepository.getOne(id);
		roomType.setType(EnumRoomType.valueOf(roomTypeDTO.getType()));
		roomType.setDescription(roomTypeDTO.getDescription());

		logger.info("Update room type with id = " + id);
		return roomTypeRepository.save(roomType);
	}

}
