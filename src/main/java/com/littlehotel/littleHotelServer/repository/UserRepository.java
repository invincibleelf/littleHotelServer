package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.entity.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long>{
	
	@Query("select u from ApplicationUser u where u.username = ?1")
	ApplicationUser findByUsername(String username);
}
