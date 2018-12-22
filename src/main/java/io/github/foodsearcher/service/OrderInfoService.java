package io.github.foodsearcher.service;

import java.util.List;

import io.github.foodsearcher.model.OrderInfo;


public interface OrderInfoService {
	public OrderInfo findByID(Long id);
	
	public OrderInfo createOrderInfo(OrderInfo orderInfo);
	
	public void delete(Long id);
	
	public OrderInfo updataOrderInfo(Long id, int state);
	
	public List<OrderInfo> findByStoreID(Long id);
	
	public List<OrderInfo> findByUserID(Long id);
}
