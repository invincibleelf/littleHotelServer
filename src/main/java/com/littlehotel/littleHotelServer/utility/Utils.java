package com.littlehotel.littleHotelServer.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.ErrorResponse;
import com.littlehotel.littleHotelServer.model.HotelDTO;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.model.SubError;

/**
 * Static Utility methods for handling validations, generating response messages
 * 
 * @author Sharad Shrestha
 *
 */
public class Utils {

	/*
	 * Method to generate response message for field validation errors for post
	 * request
	 * 
	 * @param status String
	 * 
	 * @param message String
	 * 
	 * @param fieldErrors a list of FieldErrors generated by validation plugin used
	 * 
	 * @return ErrorResponse
	 * 
	 */
	public static ErrorResponse createErrorResponse(HttpStatus status, String message, List<FieldError> fieldErrors) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(status);
		errorResponse.setMessage(message);
		List<SubError> errors = new ArrayList<>();

		if (fieldErrors != null) {
			fieldErrors.forEach(fieldError -> {
				SubError subError = new SubError();
				subError.setObject(fieldError.getObjectName());
				subError.setField(fieldError.getField());
				subError.setMessage(fieldError.getDefaultMessage());
				if (fieldError.getRejectedValue() != null) {
					subError.setRejectedValue(fieldError.getRejectedValue().toString());
				}

				errors.add(subError);
			});
		}
		errorResponse.setErrors(errors);
		return errorResponse;
	}

	/**
	 * Convert {@link Hotel} Entity Object to the respective {@link HotelDTO} object
	 * 
	 * @param hotel the Hotel entity
	 * @return hotelDTO
	 */
	public static HotelDTO convertHotelEntityToHotelDTO(Hotel hotel) {
		HotelDTO hotelDTO = new HotelDTO();

		hotelDTO.setId(hotel.getId());
		hotelDTO.setName(hotel.getName());
		hotelDTO.setCode(hotel.getCode());
		hotelDTO.setEmail(hotel.getEmail());
		hotelDTO.setPhoneNumber(hotel.getPhoneNumber());
		hotelDTO.setAddress(hotel.getAddress().getAddress());
		hotelDTO.setSuburb(hotel.getAddress().getSuburb());
		hotelDTO.setState(hotel.getAddress().getState().name());
		hotelDTO.setCountry(hotel.getAddress().getCountry().name());
		hotelDTO.setPostcode(hotel.getAddress().getPostcode());
		return hotelDTO;
	}

	public static List<HotelDTO> convertHotelEntityListToHotelDTOList(List<Hotel> hotels) {
		List<HotelDTO> hotelDTOs = new ArrayList<>();
		hotels.forEach((hotel) -> {
			HotelDTO hotelDTO = convertHotelEntityToHotelDTO(hotel);
			hotelDTOs.add(hotelDTO);

		});

		return hotelDTOs;

	}

	/**
	 * Convert {@link RoomStatus} Entity Object to the respective
	 * {@link RoomStatusDTO} object
	 * 
	 * @param roomStatus
	 * @return
	 */
	public static RoomStatusDTO convertRoomStatusEntityToDTO(RoomStatus roomStatus) {
		RoomStatusDTO roomStatusDTO = new RoomStatusDTO(roomStatus.getId(), roomStatus.getStatus().toString(),
				roomStatus.getDescription());
		return roomStatusDTO;

	}

	public static List<RoomStatusDTO> convertRoomStatusEntityListToDTO(List<RoomStatus> roomStatuses) {
		List<RoomStatusDTO> roomStatusDTOs = new ArrayList<>();
		roomStatuses.forEach((roomStatus) -> {
			RoomStatusDTO roomStatusDTO = convertRoomStatusEntityToDTO(roomStatus);
			roomStatusDTOs.add(roomStatusDTO);
		});
		return roomStatusDTOs;
	}

	/**
	 * Convert {@link RoomType} Entity Object to the respective {@link RoomTypeDTO}
	 * object
	 * 
	 * @param roomType
	 * @return
	 */
	public static RoomTypeDTO convertRoomTypeEntityToDTO(RoomType roomType) {

		RoomTypeDTO roomTypeDTO = new RoomTypeDTO(roomType.getId(), roomType.getType().toString(),
				roomType.getDescription());
		return roomTypeDTO;

	}

	public static List<RoomTypeDTO> convertRoomTypeEntityListToDTO(List<RoomType> roomTypes) {
		List<RoomTypeDTO> roomTypeDTOs = new ArrayList<>();
		roomTypes.forEach((roomType) -> {
			RoomTypeDTO roomTypeDTO = Utils.convertRoomTypeEntityToDTO(roomType);
			roomTypeDTOs.add(roomTypeDTO);
		});
		return roomTypeDTOs;
	}

	/**
	 * Convert {@link Room} Entity Object to the respective {@link RoomTypeDTO}
	 * object
	 * 
	 * @param room
	 * @return
	 */
	public static RoomDTO convertRoomEntityToDTO(Room room) {

		RoomDTO roomDTO = new RoomDTO(room.getId(), room.getName(), room.getNumber(), room.getDescription(),
				room.getRate());

		roomDTO.setHotelId(room.getHotel().getId());
		roomDTO.setHotelName(room.getHotel().getName());
		roomDTO.setRoomStatusDTO(convertRoomStatusEntityToDTO(room.getStatus()));
		roomDTO.setRoomTypeDTO(convertRoomTypeEntityToDTO(room.getType()));
		return roomDTO;
	}
	
	public static List<RoomDTO> convertRoomEntityListToDTO(List<Room> rooms){
		List<RoomDTO> roomDTOs = new ArrayList<>();
		
		rooms.forEach((room) -> {
			RoomDTO roomDTO = convertRoomEntityToDTO(room);
			roomDTOs.add(roomDTO);
		});
		
		return roomDTOs;
	}

}
