package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.constants.EnumCountry;
import com.littlehotel.littleHotelServer.constants.EnumStates;
import com.littlehotel.littleHotelServer.entity.Address;
import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.model.HotelDTO;
import com.littlehotel.littleHotelServer.repository.AddressRepository;
import com.littlehotel.littleHotelServer.repository.HotelRepository;
import com.littlehotel.littleHotelServer.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	private static final Logger logger = LogManager.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(Long id) {
		return hotelRepository.getOne(id);
	}

	@Override
	@Transactional
	public Hotel createHotel(HotelDTO hotelDTO) {

		Address address = new Address(hotelDTO.getAddress().getAddress(), hotelDTO.getAddress().getSuburb(),
				hotelDTO.getAddress().getPostcode());
		address.setState(EnumStates.valueOf(hotelDTO.getAddress().getState()));
		address.setCountry(EnumCountry.valueOf(hotelDTO.getAddress().getCountry()));

		logger.info("Request to database to save address");
		addressRepository.save(address);

		Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getCode(), hotelDTO.getPhoneNumber(), hotelDTO.getEmail(),
				address);

		logger.info("Request to database to save hotel");
		return hotelRepository.save(hotel);
	}

	@Override
	@Transactional
	public Hotel updateHotel(Long id, HotelDTO hotelDTO) {
		Hotel hotel = hotelRepository.getOne(id);
		hotel.setName(hotelDTO.getName());
		hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
		hotel.setEmail(hotelDTO.getEmail());

		hotel.getAddress().setAddress(hotelDTO.getAddress().getAddress());
		hotel.getAddress().setSuburb(hotelDTO.getAddress().getSuburb());
		hotel.getAddress().setState(EnumStates.valueOf(hotelDTO.getAddress().getState()));
		hotel.getAddress().setCountry(EnumCountry.valueOf(hotelDTO.getAddress().getCountry()));

		logger.info("Request to database to update hotel");
		return hotelRepository.save(hotel);
	}

	@Override
	public void deleteHotelById(Long id) {
		// TODO Implement Delete If Required

	}

}
