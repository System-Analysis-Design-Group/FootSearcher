package io.github.foodsearcher.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.foodsearcher.model.DishInfo;
import io.github.foodsearcher.model.StatusMsg;
import io.github.foodsearcher.service.DishInfoService;

@RestController
@RequestMapping("/dishes")
public class DishController {
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
	
	@RequestMapping(method = RequestMethod.GET)
	public DishInfo getDish(@RequestParam("id") Long id) {
		DishInfo result = new DishInfo();
		try {
			result = dishInfoService.findById(id);
		} catch (Exception exp) {
			return result;
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public StatusMsg deleteDish(@RequestParam("id") Long id) {
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
}
