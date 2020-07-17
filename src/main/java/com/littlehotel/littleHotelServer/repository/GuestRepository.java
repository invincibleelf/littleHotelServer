package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
