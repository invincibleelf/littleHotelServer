package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.littlehotel.littleHotelServer.constants.EnumCountry;
import com.littlehotel.littleHotelServer.constants.EnumStates;
import com.littlehotel.littleHotelServer.entity.Address;
import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.model.GuestDTO;
import com.littlehotel.littleHotelServer.repository.AddressRepository;
import com.littlehotel.littleHotelServer.repository.GuestRepository;
import com.littlehotel.littleHotelServer.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

	private static final Logger logger = LogManager.getLogger(GuestServiceImpl.class);

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	@Override
	public Guest getGuestById(Long id) {
		return guestRepository.getOne(id);
	}

	@Override
	@Transactional
	public Guest createGuest(GuestDTO guestDTO) {

		Address address = new Address(guestDTO.getAddress().getAddress(), guestDTO.getAddress().getSuburb(),
				guestDTO.getAddress().getPostcode());
		address.setState(EnumStates.valueOf(guestDTO.getAddress().getState()));
		address.setCountry(EnumCountry.valueOf(guestDTO.getAddress().getCountry()));

		logger.info("Request database to save address");
		addressRepository.save(address);

		Guest guest = new Guest(guestDTO.getFirstname(), guestDTO.getLastname(), guestDTO.getMobile(),
				guestDTO.getEmail(), address);
		logger.info("Request database to save guest");

		return guestRepository.save(guest);
	}

	@Override
	@Transactional
	public Guest updateGuest(Long id, GuestDTO guestDTO) {
		Guest guest = guestRepository.getOne(id);
		guest.setFirstname(guestDTO.getFirstname());
		guest.setLastname(guestDTO.getLastname());
		guest.setMobile(guestDTO.getMobile());
		guest.getAddress().setAddress(guestDTO.getAddress().getAddress());
		guest.getAddress().setSuburb(guestDTO.getAddress().getSuburb());
		guest.getAddress().setState(EnumStates.valueOf(guestDTO.getAddress().getState()));
		guest.getAddress().setCountry(EnumCountry.valueOf(guestDTO.getAddress().getCountry()));
		guest.getAddress().setPostcode(guestDTO.getAddress().getPostcode());

		logger.info("Request databse to save guest with id = " + id);
		return guestRepository.save(guest);
	}

}
