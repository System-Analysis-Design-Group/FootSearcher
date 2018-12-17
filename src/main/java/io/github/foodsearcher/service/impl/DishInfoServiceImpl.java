package io.github.foodsearcher.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.foodsearcher.dao.DishInfoDao;
import io.github.foodsearcher.model.DishInfo;
import io.github.foodsearcher.service.DishInfoService;

@Service
@Transactional
public class DishInfoServiceImpl implements DishInfoService{
	@Autowired
	private DishInfoDao dishInfoDao;
	
	public DishInfo findById(Long id) {
		System.out.println("DishInfoServiceImpl.findByID()");
        return dishInfoDao.findById(id);
	}
	
	public DishInfo createDishInfo(DishInfo dishInfo) {
		return dishInfoDao.save(dishInfo);
	}
	
	public void delete(Long id) {
		System.out.println("DishInfoServiceImpl.deleteByID()");
		dishInfoDao.delete(id);
	}
	
	public DishInfo updataDishInfo(DishInfo dishInfo) {
		System.out.println("DishInfoServiceImpl.updateDishInfo()");
        return dishInfoDao.save(dishInfo);
	}
}	
