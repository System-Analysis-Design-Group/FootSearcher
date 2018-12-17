package io.github.foodsearcher.dao;

import org.springframework.data.repository.CrudRepository;

import io.github.foodsearcher.model.GoodInfo;

public interface GoodInfoDao extends CrudRepository<GoodInfo,Long>{

	public GoodInfo findById(Long id);
	
	public GoodInfo save(GoodInfo goodInfo);
}
