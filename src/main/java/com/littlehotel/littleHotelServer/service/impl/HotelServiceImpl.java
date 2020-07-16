package com.littlehotel.littleHotelServer.service.impl;

import java.util.List;

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
	public Hotel createHotel(HotelDTO hotelDTO) {
		Address address = new Address(hotelDTO.getAddress(), hotelDTO.getSuburb(), hotelDTO.getPostcode());
		address.setState(EnumStates.valueOf(hotelDTO.getState()));
		address.setCountry(EnumCountry.valueOf(hotelDTO.getCountry()));

		addressRepository.save(address);

		Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getCode(), hotelDTO.getPhoneNumber(), hotelDTO.getEmail(),
				address);

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel updateHotel(Long id, HotelDTO hotelDTO) {
		Hotel hotel = hotelRepository.getOne(id);
		hotel.setName(hotelDTO.getName());
		hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
		hotel.setEmail(hotelDTO.getEmail());
		
		hotel.getAddress().setAddress(hotelDTO.getAddress());
		hotel.getAddress().setSuburb(hotelDTO.getSuburb());
		hotel.getAddress().setState(EnumStates.valueOf(hotelDTO.getState()));
		hotel.getAddress().setCountry(EnumCountry.valueOf(hotelDTO.getCountry()));
		
		hotelRepository.save(hotel);
		return hotel;
	}

	@Override
	public void deleteHotelById(Long id) {
		// TODO Implement Delete If Required

	}

}
