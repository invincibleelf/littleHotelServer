package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.RoomStatus;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long>{

}
