package com.littlehotel.littleHotelServer.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.littlehotel.littleHotelServer.entity.BaseEntity;
import com.littlehotel.littleHotelServer.model.BaseDTO;

public class GenericMapperUtil {
	
	@Autowired
	protected ModelMapper mapper;
	
	/**
	 * Generic Method to convert list of source dtos to respective entities and vice versa
	 * @param <S> Source type of {@link BaseDTO} or {@link BaseEntity}
	 * @param <T> Destination type of {@link BaseEntity} or {@link BaseDTO}
	 * @param source 
	 * @param targetClass
	 * @return
	 */
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		List<T> list =  source.stream().map(element -> mapper.map(element, targetClass)).collect(Collectors.toList());
		
		return list;

	}

	/**
	 * Generic Method to convert dto to respective entity and vice versa
	 * @param <S> Source type of {@link BaseDTO} or {@link BaseEntity}
	 * @param <T> Destination type of {@link BaseEntity} or {@link BaseDTO}
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public <S, T> T mapModel(S source, Class<T> targetClass) {
		return mapper.map(source, targetClass);
	}
	
	/** Generic method to convert dto to entity for update operation
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 */
	public <S, T> void mapModel(S source, T target) {
		mapper.map(source, target);
	}
	

}
