package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.RoomType;

@Repository
public interface RoomTypeRepository  extends JpaRepository<RoomType, Long>{

}
