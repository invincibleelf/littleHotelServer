package com.littlehotel.littleHotelServer.service;

import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.Hotel;
import com.littlehotel.littleHotelServer.model.HotelDTO;

@Service
public class HotelService extends GenericService<HotelDTO, Hotel> {

	@Override
	public HotelDTO create(HotelDTO dto) throws Exception {
		Hotel hotel = mapperUtil.mapModel(dto, entityClass);
		repository.save(hotel);
		return mapperUtil.mapModel(hotel, dtoClass);
	}

	@Override
	public HotelDTO update(Long id, HotelDTO dto) {
		Hotel hotel = repository.getOne(id);
		mapperUtil.mapModel(dto,hotel);
		repository.save(hotel);
		return mapperUtil.mapModel(hotel, dtoClass) ;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
