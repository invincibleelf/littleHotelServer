package com.littlehotel.littleHotelServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.littlehotel.littleHotelServer.constants.EnumAppUserStatus;
import com.littlehotel.littleHotelServer.entity.ApplicationUser;

@Repository
public interface UserRepository extends GenericRepository<ApplicationUser> {

	@Query("select u from ApplicationUser u where u.username = ?1")
	Optional<ApplicationUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	@Query("select u from ApplicationUser u where u.status = :status and u.username= :username ")
	Optional<ApplicationUser> findByStatusAndUsername(@Param("status") EnumAppUserStatus status,
			@Param("username") String username);
}
