package com.littlehotel.littleHotelServer.utility;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.ErrorResponse;
import com.littlehotel.littleHotelServer.model.GuestDTO;
import com.littlehotel.littleHotelServer.model.HotelDTO;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.model.RoomDTO;
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.model.SubError;

/**
 * Static Utility methods for handling validations, generating response messages
 * and converting entities to DTOs
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
	public static HotelDTO convertHotelEntityToHotelDTO(Hotel hotel, ModelMapper mapper) {
		HotelDTO hotelDTO = mapper.map(hotel, HotelDTO.class);
		return hotelDTO;
	}

	public static List<HotelDTO> convertHotelEntityListToHotelDTOList(List<Hotel> hotels, ModelMapper mapper) {
		List<HotelDTO> hotelDTOs = new ArrayList<>();
		hotels.forEach((hotel) -> {
			HotelDTO hotelDTO = convertHotelEntityToHotelDTO(hotel, mapper);
			hotelDTOs.add(hotelDTO);

		});

		return hotelDTOs;

	}

	/**
	 * Convert {@link HotelDTO} Object to the respective {@link Hotel} entity object
	 * 
	 * @param hotelDTO
	 * @return hotel
	 */
	public static Hotel convertHotelDTOToEntity(HotelDTO hotelDTO, ModelMapper mapper) {
		Hotel hotel = mapper.map(hotelDTO, Hotel.class);
		return hotel;
	}

	/**
	 * Convert {@link RoomStatus} Entity Object to the respective
	 * {@link RoomStatusDTO} object
	 * 
	 * @param roomStatus
	 * @return
	 */
	public static RoomStatusDTO convertRoomStatusEntityToDTO(RoomStatus roomStatus, ModelMapper mapper) {
		RoomStatusDTO roomStatusDTO = mapper.map(roomStatus, RoomStatusDTO.class);
		return roomStatusDTO;

	}

	public static List<RoomStatusDTO> convertRoomStatusEntityListToDTO(List<RoomStatus> roomStatuses,
			ModelMapper mapper) {
		List<RoomStatusDTO> roomStatusDTOs = new ArrayList<>();
		roomStatuses.forEach((roomStatus) -> {
			RoomStatusDTO roomStatusDTO = convertRoomStatusEntityToDTO(roomStatus, mapper);
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
	public static RoomTypeDTO convertRoomTypeEntityToDTO(RoomType roomType, ModelMapper mapper) {

		RoomTypeDTO roomTypeDTO = mapper.map(roomType, RoomTypeDTO.class);
		return roomTypeDTO;

	}

	public static List<RoomTypeDTO> convertRoomTypeEntityListToDTO(List<RoomType> roomTypes, ModelMapper mapper) {
		List<RoomTypeDTO> roomTypeDTOs = new ArrayList<>();
		roomTypes.forEach((roomType) -> {
			RoomTypeDTO roomTypeDTO = Utils.convertRoomTypeEntityToDTO(roomType, mapper);
			roomTypeDTOs.add(roomTypeDTO);
		});
		return roomTypeDTOs;
	}

	/**
	 * Convert {@link Room} Entity Object to the respective {@link RoomTypeDTO}
	 * object
	 * 
	 * @param room
	 * @param mapper the {@link ModelMapper} used for conversion
	 * @return
	 */

	public static RoomDTO convertRoomEntityToDTO(Room room, ModelMapper mapper) {

		RoomDTO roomDTO = mapper.map(room, RoomDTO.class);
		return roomDTO;
	}

	public static List<RoomDTO> convertRoomEntityListToDTO(List<Room> rooms, ModelMapper mapper) {
		List<RoomDTO> roomDTOs = new ArrayList<>();

		rooms.forEach((room) -> {
			RoomDTO roomDTO = convertRoomEntityToDTO(room, mapper);
			roomDTOs.add(roomDTO);
		});

		return roomDTOs;
	}

	public static ReservationDTO convertReservationEntityToDTO(Reservation reservation, ModelMapper mapper) {

		ReservationDTO reservationDTO = mapper.map(reservation, ReservationDTO.class);

		return reservationDTO;
	}

	public static List<ReservationDTO> convertReservationEnitityListToDTO(List<Reservation> reservations,
			ModelMapper mapper) {
		List<ReservationDTO> reservationDTOs = new ArrayList<>();

		reservations.forEach((reservation) -> {
			ReservationDTO reservationDTO = convertReservationEntityToDTO(reservation, mapper);
			reservationDTOs.add(reservationDTO);
		});

		return reservationDTOs;
	}

	public static GuestDTO convertGuestEntityToDTO(Guest guest, ModelMapper mapper) {

		GuestDTO guestDTO = mapper.map(guest, GuestDTO.class);
		return guestDTO;
	}

	public static List<GuestDTO> convertGuestEntityListToDTO(List<Guest> guests, ModelMapper mapper) {
		List<GuestDTO> guestDTOs = new ArrayList<>();
		guests.forEach((guest) -> {
			GuestDTO guestDTO = convertGuestEntityToDTO(guest, mapper);
			guestDTOs.add(guestDTO);
		});
		return guestDTOs;
	}
}
