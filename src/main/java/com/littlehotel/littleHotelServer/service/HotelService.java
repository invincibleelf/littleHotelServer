package com.littlehotel.littleHotelServer.service;

import java.util.List;

import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.model.HotelDTO;

/**
 * @author Sharad Shrestha
 *
 */
public interface HotelService {

	List<Hotel> getAllHotels();

	Hotel getHotelById(Long id);

	Hotel createHotel(HotelDTO hotelDTO);

	Hotel updateHotel(Long id, HotelDTO hotelDTO);

	void deleteHotelById(Long id);

}
