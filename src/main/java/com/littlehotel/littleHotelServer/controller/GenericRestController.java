package com.littlehotel.littleHotelServer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.littlehotel.littleHotelServer.entity.BaseEntity;
import com.littlehotel.littleHotelServer.model.BaseDTO;
import com.littlehotel.littleHotelServer.service.GenericService;

public abstract class GenericRestController<S extends GenericService<D, E>, D extends BaseDTO, E extends BaseEntity> {
	
	@Autowired
	protected GenericService<D, E> service;
	
	@GetMapping
	public ResponseEntity<?> all(){
		return ResponseEntity.ok().body(service.all());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		
		return ResponseEntity.ok().body(service.get(id));
	}
	
	@PostMapping( consumes = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody D dto) throws Exception{
	
		return ResponseEntity.ok().body(service.create(dto));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id")Long id, @Valid @RequestBody D dto){
	
		return ResponseEntity.ok().body(service.update(id, dto));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().body("Deleted successfully");
	}
	


}
