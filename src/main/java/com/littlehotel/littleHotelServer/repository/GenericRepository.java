package com.littlehotel.littleHotelServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.littlehotel.littleHotelServer.entity.BaseEntity;

public interface GenericRepository <T extends BaseEntity> extends JpaRepository<T, Long> {

}
