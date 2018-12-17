package io.github.foodsearcher.service;

import io.github.foodsearcher.model.GoodInfo;


public interface GoodInfoService {
	public GoodInfo findById(Long id);
	
	public GoodInfo createGoodInfo(GoodInfo goodInfo);
}
