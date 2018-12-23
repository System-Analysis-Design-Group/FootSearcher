package io.github.foodsearcher.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import io.github.foodsearcher.model.OrderInfo;

import java.util.List;

import javax.persistence.Entity;


public interface OrderInfoDao extends CrudRepository<OrderInfo,Long>{
	
	public OrderInfo findByID(Long id);
	
	public OrderInfo save(OrderInfo orderInfo);
	
	public void delete(Long id);
	
	public List<OrderInfo> findAllByStoreID(Long id);
	
	public List<OrderInfo> findAllByUserID(Long id);
}
