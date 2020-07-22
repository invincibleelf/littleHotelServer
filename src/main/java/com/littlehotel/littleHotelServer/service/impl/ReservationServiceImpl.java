package com.littlehotel.littleHotelServer.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumBookingStatus;
import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Invoice;
import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.GuestDTO;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.repository.GuestRepository;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.repository.InvoiceRepository;
import com.littlehotel.littleHotelServer.repository.ReservationRepository;
import com.littlehotel.littleHotelServer.repository.RoomRepository;
import com.littlehotel.littleHotelServer.repository.RoomTypeRepository;
import com.littlehotel.littleHotelServer.service.ReservationService;
import com.littlehotel.littleHotelServer.utility.AvailableRoomLessThanBookedException;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

	@Autowired
	private GuestServiceImpl guestService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private HotelRepository HotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation getReservationById(Long id) {
		return reservationRepository.getOne(id);
	}

	@Transactional
	@Override
	public Reservation createReservation(ReservationDTO reservationDTO) throws AvailableRoomLessThanBookedException {
		Reservation reservation = new Reservation(reservationDTO.getDateFrom(), reservationDTO.getDateTo(),
				reservationDTO.getAdult(), reservationDTO.getChild(), EnumBookingStatus.ACTIVE);

		Hotel hotel = HotelRepository.getOne(reservationDTO.getHotelId());
		reservation.setHotel(hotel);

		List<Map<String, Long>> roomTypeCountMapList = reservationDTO.getRoomTypeCountMapList();
		List<Room> bookedRooms = new ArrayList<Room>();
		BigDecimal amount = BigDecimal.ZERO;
		for (Map<String, Long> map : roomTypeCountMapList) {
			Long roomTypeId = map.get("roomTypeId");
			RoomType roomType = roomTypeRepository.getOne(roomTypeId);

			logger.info("Request Database to get available rooms by type " + roomTypeId + " and for dates "
					+ reservationDTO.getDateFrom() + " and " + reservationDTO.getDateTo());
			List<Room> availableRooms = roomRepository.getAvailableRoomsForBookingByTypeAndStatus(
					reservationDTO.getDateFrom(), reservationDTO.getDateTo(), hotel.getId(), EnumRoomStatus.OOO,
					roomType.getType());

			Long count = map.get("count");

			/*
			 * Check if the available rooms is greater than requested booking Add the
			 * required number to the reservation which are at first in the list If not
			 * throw Exception
			 */
			if (availableRooms.size() >= count) {
				for (int j = 0; j < count; j++) {
					bookedRooms.add(availableRooms.get(j));
					amount = amount.add(availableRooms.get(j).getType().getRate());
				}
			} else {
				throw new AvailableRoomLessThanBookedException("Available Room of type " + roomType.getType().name()
						+ " is less that booked count of " + count, roomTypeId.toString());
			}
		}
		System.out.print(amount);
		reservation.setRooms(new HashSet<>(bookedRooms));

		GuestDTO guestDTO = reservationDTO.getGuest();

		logger.info("Request database to get Guest with email " + guestDTO.getEmail());
		Optional<?> optional = guestRepository.findByEmail(guestDTO.getEmail());

		// Create a new guest if not exists
		if (optional.isEmpty()) {
			Guest guest = guestService.createGuest(guestDTO);
			reservation.setGuest(guest);
		} else {
			reservation.setGuest((Guest) optional.get());
		}

		logger.info("Request database to save reservation");
		reservationRepository.save(reservation);

		// Create Invoice after creating reservation
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		invoice.setGuest(reservation.getGuest());
		invoice.setReservation(reservation);
		logger.info("Request database to save invoice");
		invoiceRepository.save(invoice);

		return reservation;
	}

	@Transactional
	@Override
	public Reservation updateReservation(Long id, ReservationDTO reservationDTO) {

		// TODO Implement update based on scenario and case
		Reservation reservation = reservationRepository.getOne(id);

		guestService.updateGuest(reservation.getGuest().getId(), reservationDTO.getGuest());

		return reservationRepository.save(reservation);
	}

	@Override
	public List<RoomTypeDTO> getAvailableRoomTypesAndRoomCount(LocalDate dateFrom, LocalDate dateTo, Long hotelId) {

		return roomRepository.getAvailableRoomTypesAndRoomCount(dateFrom, dateTo, hotelId, EnumRoomStatus.OOO);
	}

}
