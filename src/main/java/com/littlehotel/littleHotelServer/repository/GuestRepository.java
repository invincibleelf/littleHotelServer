package com.littlehotel.littleHotelServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.Guest;;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	@Query("select g from Guest g where g.email=:email")
	Optional<Guest> findByEmail(String email);
}
