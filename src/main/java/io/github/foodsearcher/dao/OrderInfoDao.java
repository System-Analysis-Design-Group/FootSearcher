package io.github.foodsearcher.dao;

import org.springframework.data.repository.CrudRepository;

import io.github.foodsearcher.model.OrderInfo;

import javax.persistence.Entity;


public interface OrderInfoDao extends CrudRepository<OrderInfo,Long>{
	
	public OrderInfo findByID(Long id);
	
	public OrderInfo save(OrderInfo orderInfo);
	
	public void delete(Long id);
}
