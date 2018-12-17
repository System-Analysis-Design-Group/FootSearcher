package io.github.foodsearcher.service;

import io.github.foodsearcher.model.OrderInfo;


public interface OrderInfoService {
	public OrderInfo findByID(Long id);
	
	public OrderInfo createOrderInfo(OrderInfo orderInfo);
	
	public void delete(Long id);
	
	public OrderInfo updataOrderInfo(Long id, int state);
}
