package io.github.foodsearcher.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.foodsearcher.model.DishInfo;

public interface DishInfoDao extends CrudRepository<DishInfo,Long>{
	public DishInfo findById(Long id);
	
	public DishInfo save(DishInfo dishInfo);
	
	public void delete(Long id);
	
	public List<DishInfo> findAllByStoreId(Long id);
}
