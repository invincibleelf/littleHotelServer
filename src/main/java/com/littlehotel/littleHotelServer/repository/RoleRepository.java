package com.littlehotel.littleHotelServer.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.constants.EnumRole;
import com.littlehotel.littleHotelServer.entity.ApplicationRole;

@Repository
public interface RoleRepository extends GenericRepository<ApplicationRole>{
	
	Optional<ApplicationRole> findByName(EnumRole name);

}
