package io.github.foodsearcher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.foodsearcher.dao.OrderInfoDao;
import io.github.foodsearcher.model.OrderInfo;
import io.github.foodsearcher.service.OrderInfoService;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService{

	@Autowired
	private OrderInfoDao orderInfoDao;
	
	public OrderInfo findByID(Long id) {
		System.out.println("OrderInfoServiceImpl.findByID()");
        return orderInfoDao.findByID(id);
	}
	
	public OrderInfo createOrderInfo(OrderInfo orderInfo) {
		return orderInfoDao.save(orderInfo);
	}
	
	public void delete(Long id) {
		System.out.println("OrderInfoServiceImpl.deleteByID()");
		orderInfoDao.delete(id);
	}
	
	public OrderInfo updataOrderInfo(Long id, int state) {
		System.out.println("OrderInfoServiceImpl.updateOrderInfo()");
		OrderInfo orderInfo = orderInfoDao.findByID(id);
		orderInfo.setState(state);
        return orderInfoDao.save(orderInfo);
	}
}
