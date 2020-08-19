package com.littlehotel.littleHotelServer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.constants.EnumRoomType;
import com.littlehotel.littleHotelServer.entity.Room;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;

@Repository
public interface RoomRepository extends GenericRepository<Room> {

	@Query("Select r from Room r inner join r.status s where s.status=:status")
	List<Room> findAllByStatus(EnumRoomStatus status);

	// TODO Optimize query
	/**
	 * Query to get available rooms based on reservation date
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param hotelId
	 * @param status
	 * @param type
	 * @return
	 */
	@Query("SELECT DISTINCT r FROM Room r JOIN r.type t JOIN r.status s JOIN r.hotel h "
			+ "where h.id =:hotelId and s.status<>:status and t.type=:type and r.id "
			+ "NOT IN(SELECT r.id FROM Reservation re JOIN re.rooms r where re.dateFrom<:dateTo and re.dateTo >:dateFrom and re.status='ACTIVE')")
	List<Room> getAvailableRoomsForBookingByTypeAndStatus(LocalDate dateFrom, LocalDate dateTo, Long hotelId,
			EnumRoomStatus status, EnumRoomType type);

	// TODO Optimize query
	/**
	 * Query to get available rooms types with number of available room counts based
	 * on reservations on provided date
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param hotelId
	 * @param status   except Out of order rooms
	 * @return RoomTypeDTO
	 */
	@Query("SELECT  new com.littlehotel.littleHotelServer.model.RoomTypeDTO(t.id,t.type,t.description,t.rate, count(distinct r.id)) FROM Room r JOIN r.type t JOIN r.status s JOIN r.hotel h "
			+ "where h.id =:hotelId and s.status<>:status and r.id "
			+ "NOT IN(SELECT r.id FROM Reservation re JOIN re.rooms r where re.dateFrom<:dateTo and re.dateTo >:dateFrom and re.status='ACTIVE') group by t.id")
	List<RoomTypeDTO> getAvailableRoomTypesAndRoomCount(LocalDate dateFrom, LocalDate dateTo, Long hotelId,
			EnumRoomStatus status);

}
