package io.github.foodsearcher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.foodsearcher.model.DishInfo;
import io.github.foodsearcher.model.StatusMsg;
import io.github.foodsearcher.service.DishInfoService;

@RestController
@RequestMapping("/dishes")
public class DishController {
	
	@Autowired
	private DishInfoService dishInfoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public StatusMsg createDish(DishInfo dishInfo) {
		try {
			dishInfoService.createDishInfo(dishInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(value = "/{dishId}",method = RequestMethod.GET)
	public StatusMsg getDish(@PathVariable("dishId") Long id) {
		DishInfo result;
		try {
			result = dishInfoService.findById(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
	@RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE)
	public StatusMsg deleteDish(@PathVariable("dishid") Long id) {
		try {
			dishInfoService.delete(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public StatusMsg updateDish(DishInfo dishInfo) {
		try {
			dishInfoService.updataDishInfo(dishInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(value="/store/{storeId}",method = RequestMethod.GET)
	public StatusMsg getStoreDish(@PathVariable("storeId") Long id) {
		List<DishInfo> result;
		try {
			result = dishInfoService.findByStoreId(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
}
