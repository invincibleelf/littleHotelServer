package com.littlehotel.littleHotelServer.service;

import java.util.List;

import javax.validation.Valid;

import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;

public interface RoomService {

	List<Room> getRooms();

	List<Room> getRoomsByStatus(String status);

	Room getRoomById(Long id);

	Room createRoom(RoomDTO roomDTO) throws Exception;

	Room updateRoom(Long id, @Valid RoomDTO roomDTO) throws Exception;

	List<RoomStatus> getRoomStatuses();

	RoomStatus getRoomStatusById(Long id);

	RoomStatus createRoomStatus(RoomStatusDTO roomStatusDTO);

	RoomStatus updateRoomStatus(Long id, RoomStatusDTO roomStatusDTO);

	List<RoomType> getRoomTypes();

	RoomType getRoomTypeById(Long id);

	RoomType createRoomType(RoomTypeDTO roomTypeDTO);

	RoomType updateRoomType(Long id, RoomTypeDTO roomTypeDTO);

}
