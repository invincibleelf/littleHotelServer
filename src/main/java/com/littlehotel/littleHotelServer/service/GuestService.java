package com.littlehotel.littleHotelServer.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.littlehotel.littleHotelServer.entity.Guest;
import com.littlehotel.littleHotelServer.model.GuestDTO;

@Service
public class GuestService extends GenericService<GuestDTO, Guest> {

	/** Return type different  from generic  create
	 * @param guestDTO
	 * @return Guest
	 */
	@Transactional
	public Guest createGuest(GuestDTO guestDTO) {
		Guest guest = mapperUtil.mapModel(guestDTO, entityClass);
		return repository.save(guest);
	}

}
