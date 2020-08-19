package com.littlehotel.littleHotelServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlehotel.littleHotelServer.entity.RoomStatus;
import com.littlehotel.littleHotelServer.model.RoomStatusDTO;
import com.littlehotel.littleHotelServer.service.RoomStatusService;

@RestController
@RequestMapping("/api/room-status")
public class RoomStatusController extends GenericRestController<RoomStatusService, RoomStatusDTO, RoomStatus>{

}
