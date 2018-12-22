package io.github.foodsearcher.service;

import java.util.List;

import io.github.foodsearcher.model.DishInfo;

public interface DishInfoService {
	public DishInfo findById(Long id);
	
	public DishInfo createDishInfo(DishInfo dishInfo);
	
	public void delete(Long id);
	
	public DishInfo updataDishInfo(DishInfo dishInfo);
	
	public List<DishInfo> findByStoreId(Long id);
}
