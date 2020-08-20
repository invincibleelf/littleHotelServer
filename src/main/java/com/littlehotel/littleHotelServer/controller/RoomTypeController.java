package com.littlehotel.littleHotelServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.RoomType;
import com.littlehotel.littleHotelServer.model.RoomTypeDTO;
import com.littlehotel.littleHotelServer.service.RoomTypeService;

@RestController
@RequestMapping("/api/room-type")
public class RoomTypeController extends GenericRestController<RoomTypeService, RoomTypeDTO, RoomType>{

}
