package io.github.foodsearcher.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.github.foodsearcher.model.OrderInfo;
import io.github.foodsearcher.model.StatusMsg;
import io.github.foodsearcher.service.OrderInfoService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderInfoService orderInfoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public StatusMsg createOrder(OrderInfo orderInfo) {
		try {
			orderInfoService.createOrderInfo(orderInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public OrderInfo getOrder(@RequestParam("id") Long id) {
		OrderInfo result = new OrderInfo();
		try {
			result = orderInfoService.findByID(id);
		} catch (Exception exp) {
			return result;
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public StatusMsg deleteOrder(@RequestParam("id") Long id) {
		try {
			orderInfoService.delete(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public StatusMsg updateOrder(@RequestParam("id") Long id,
								 @RequestParam("state") int state) {
		try {
			orderInfoService.updataOrderInfo(id, state);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
}
