package com.littlehotel.littleHotelServer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumBookingStatus;
import com.littlehotel.littleHotelServer.constants.EnumCountry;
import com.littlehotel.littleHotelServer.constants.EnumStates;
import com.littlehotel.littleHotelServer.entity.Address;
import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.entity.Reservation;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.model.GuestDTO;
import com.littlehotel.littleHotelServer.model.ReservationDTO;
import com.littlehotel.littleHotelServer.repository.AddressRepository;
import com.littlehotel.littleHotelServer.repository.GuestRepository;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.repository.ReservationRepository;
import com.littlehotel.littleHotelServer.repository.RoomRepository;
import com.littlehotel.littleHotelServer.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private HotelRepository HotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private AddressRepository addressRepository;

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
	public Reservation createReservation(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation(reservationDTO.getDateFrom(), reservationDTO.getDateTo(),
				reservationDTO.getCount(), EnumBookingStatus.ACTIVE);

		Hotel hotel = HotelRepository.getOne(reservationDTO.getHotelId());
		reservation.setHotel(hotel);

		Set<Long> roomIds = reservationDTO.getRoomIds();
		Set<Room> rooms = new HashSet<>(roomRepository.findAllById(roomIds));
		reservation.setRooms(rooms);

		GuestDTO guestDTO = reservationDTO.getGuest();

		Optional<?> optional = guestRepository.findByEmail(guestDTO.getEmail());

		if (optional.isEmpty()) {
			Address address = new Address(guestDTO.getAddress().getAddress(), guestDTO.getAddress().getSuburb(),
					guestDTO.getAddress().getPostcode());
			address.setState(EnumStates.valueOf(guestDTO.getAddress().getState()));
			address.setCountry(EnumCountry.valueOf(guestDTO.getAddress().getCountry()));

			logger.info("Request database to save address");
			addressRepository.save(address);

			Guest guest = new Guest(guestDTO.getFirstname(), guestDTO.getLastname(), guestDTO.getMobile(),
					guestDTO.getEmail(), address);
			logger.info("Request database to save guest");
			
			guestRepository.save(guest);
			reservation.setGuest(guest);
		} else {
			reservation.setGuest((Guest) optional.get());
		}

		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation updateReservation(Long id, ReservationDTO reservationDTO) {
		// TODO Implement when necessary
		return null;
	}

}
