package com.littlehotel.littleHotelServer.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.littlehotel.littleHotelServer.entity.BaseEntity;
import com.littlehotel.littleHotelServer.model.BaseDTO;
import com.littlehotel.littleHotelServer.repository.GenericRepository;
import com.littlehotel.littleHotelServer.utility.GenericMapperUtil;

public abstract class GenericService<D extends BaseDTO, E extends BaseEntity> {
	
	protected final Class<D> dtoClass;
	
	protected final Class<E> entityClass;
	
	@Autowired
	protected GenericRepository<E> repository;
	
	@Autowired
	protected GenericMapperUtil mapperUtil;
	
	public GenericService() {
		this.dtoClass = getDtoClass();
		this.entityClass = getEntityClass();
	}
	
	public List<D> all() {
		return (List<D>) mapperUtil.mapList(repository.findAll(), dtoClass);

	}
	
	public D get(@PathVariable("id") Long id) {
		return mapperUtil.mapModel(repository.getOne(id), dtoClass);
	}
	
	@Transactional
	public abstract D create(D dto) throws Exception;
	
	@Transactional
	public abstract D update(Long id , D dto);
	
	public abstract void delete(Long id);
	
		
	/** Method to get class type of implementation class of {@link BaseDTO}
	 * @return 
	 */
	@SuppressWarnings("unchecked")
    private Class<D> getDtoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass())
           .getActualTypeArguments()[0];
    }
	
	/** Method to get class type of implementation class of {@link BaseEntity}
	 * @return 
	 */
	@SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
           .getActualTypeArguments()[1];
    }

}
