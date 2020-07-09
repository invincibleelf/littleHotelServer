package com.littlehotel.littleHotelServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.constants.EnumRole;
import com.littlehotel.littleHotelServer.entity.ApplicationRole;

@Repository
public interface RoleRepository extends JpaRepository<ApplicationRole, Long>{
	
	Optional<ApplicationRole> findByName(EnumRole name);

}
