package com.littlehotel.littleHotelServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.constants.EnumRoomStatus;
import com.littlehotel.littleHotelServer.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("Select r from Room r inner join r.status s where s.status=:status")
	List<Room> findAllByStatus(EnumRoomStatus status);

}
