package com.littlehotel.littleHotelServer.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumBookingStatus;
import com.littlehotel.littleHotelServer.constants.EnumPaymentType;
import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Invoice;
import com.littlehotel.littleHotelServer.entity.Payment;
import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.GuestDTO;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.repository.GuestRepository;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.repository.InvoiceRepository;
import com.littlehotel.littleHotelServer.repository.RoomRepository;
import com.littlehotel.littleHotelServer.repository.RoomTypeRepository;
import com.littlehotel.littleHotelServer.utility.AvailableRoomLessThanBookedException;
import com.stripe.exception.StripeException;

@Service
public class ReservationService extends GenericService<ReservationDTO, Reservation> {

	private static final Logger logger = LogManager.getLogger(ReservationService.class);

	@Autowired
	private GuestService guestService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public ReservationDTO create(ReservationDTO reservationDTO) throws StripeException {
		Reservation reservation = new Reservation(reservationDTO.getDateFrom(), reservationDTO.getDateTo(),
				reservationDTO.getAdult(), reservationDTO.getChild(), EnumBookingStatus.ACTIVE);

		Hotel hotel = hotelRepository.getOne(reservationDTO.getHotelId());
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

		reservation.setRooms(new HashSet<>(bookedRooms));

		GuestDTO guestDTO = reservationDTO.getGuest();

		Payment payment = paymentService.createPayment(guestDTO.getEmail(), amount.intValue(),
				reservationDTO.getStripeToken(), EnumPaymentType.valueOf(reservationDTO.getPaymentType()));

		logger.info("Request database to get Guest with email " + guestDTO.getEmail());
		Optional<?> optional = guestRepository.findByEmail(guestDTO.getEmail());

		// Create a new guest if not exists
		if (optional.isEmpty()) {
			Guest guest = guestService.createGuest(guestDTO);
			reservation.setGuest(guest);
		} else {
			reservation.setGuest((Guest) optional.get());
		}

		

		// Create Invoice after creating reservation
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		invoice.setGuest(reservation.getGuest());
		invoice.setPayment(payment);

		logger.info("Request database to save invoice");
		invoiceRepository.save(invoice);
		
		reservation.setInvoice(invoice);
		
		logger.info("Request database to save reservation");
		repository.save(reservation);

		return mapperUtil.mapModel(reservation, dtoClass);

	}

	@Override
	public ReservationDTO update(Long id, ReservationDTO reservationDTO) throws Exception {
		// TODO Implement update based on scenario and case
		Reservation reservation = repository.getOne(id);

		guestService.update(reservation.getGuest().getId(), reservationDTO.getGuest());

		return mapperUtil.mapModel(repository.save(reservation),dtoClass) ;
	}
	
	public List<RoomTypeDTO> getAvailableRoomTypesAndRoomCount(LocalDate dateFrom, LocalDate dateTo, Long hotelId) {

		return roomRepository.getAvailableRoomTypesAndRoomCount(dateFrom, dateTo, hotelId, EnumRoomStatus.OOO);
	}
}
