package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
