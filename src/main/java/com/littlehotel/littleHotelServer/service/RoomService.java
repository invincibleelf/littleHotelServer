package com.littlehotel.littleHotelServer.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.repository.RoomRepository;
import com.littlehotel.littleHotelServer.repository.RoomStatusRepository;
import com.littlehotel.littleHotelServer.repository.RoomTypeRepository;

@Service
public class RoomService extends GenericService<RoomDTO, Room> {
	private final static Logger logger = LogManager.getLogger(RoomService.class);

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomStatusRepository roomStatusRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public List<RoomDTO> allByStatus(String status) {
		EnumRoomStatus st = EnumRoomStatus.valueOf(status);
		logger.info("Database call to get rooms by status = " + st);
		return mapperUtil.mapList(roomRepository.findAllByStatus(st), dtoClass);
	}

	@Override
	public RoomDTO create(RoomDTO roomDTO) throws Exception {
		Room room = new Room(roomDTO.getName(), roomDTO.getNumber(), roomDTO.getDescription());

		logger.info("Database call to get hotel for id = " + roomDTO.getHotelId());
		Hotel hotel = hotelRepository.getOne(roomDTO.getHotelId());
		room.setHotel(hotel);

		/*
		 * Check if room status id and room type is null. Important as the id in the
		 * DTOs are not validated by Spring validation
		 */
		if (roomDTO.getRoomStatus().getId() == null || roomDTO.getRoomType().getId() == null) {
			// TODO Create custom exception to handle this case
			throw new Exception("Field id is null");
		}
		logger.info("Database call to get room status for id = " + roomDTO.getRoomStatus().getId());
		RoomStatus status = roomStatusRepository.getOne(roomDTO.getRoomStatus().getId());
		room.setStatus(status);

		logger.info("Database call to get room type for id = " + roomDTO.getRoomType().getId());
		RoomType type = roomTypeRepository.getOne(roomDTO.getRoomType().getId());
		room.setType(type);

		logger.info("Database call to save room");
		return mapperUtil.mapModel(repository.save(room),dtoClass);
	}
	
	@Override
	public  RoomDTO update(Long id, RoomDTO roomDTO) throws Exception {
		Room room = roomRepository.getOne(id);

		room.setName(roomDTO.getName());
		room.setNumber(roomDTO.getNumber());
		room.setDescription(roomDTO.getDescription());

		/*
		 * Check if room status id and room type is null. Important as the id in the
		 * DTOs are not validated by Spring validation
		 */
		if (roomDTO.getRoomStatus().getId() == null || roomDTO.getRoomType().getId() == null) {
			// TODO Create custom exception to handle this case
			throw new Exception("Field id is null");
		}

		logger.info("Database call to get room status for id = " + roomDTO.getRoomStatus().getId());
		RoomStatus status = roomStatusRepository.getOne(roomDTO.getRoomStatus().getId());
		room.setStatus(status);

		logger.info("Database call to get room type for id = " + roomDTO.getRoomType().getId());
		RoomType type = roomTypeRepository.getOne(roomDTO.getRoomType().getId());
		room.setType(type);

		logger.info("Database call to save room");
		return mapperUtil.mapModel(roomRepository.save(room), dtoClass);
	}
	
	

}
