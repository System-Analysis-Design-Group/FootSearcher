package io.github.foodsearcher.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.foodsearcher.dao.GoodInfoDao;
import io.github.foodsearcher.model.GoodInfo;
import io.github.foodsearcher.service.GoodInfoService;

@Service
@Transactional
public class GoodInfoServiceImpl implements GoodInfoService{
	
	@Autowired
	private GoodInfoDao goodInfoDao;
	
	public GoodInfo findById(Long id) {
		System.out.println("GoodInfoServiceImpl.findById()");
        return goodInfoDao.findById(id);
	}
	
	public GoodInfo createGoodInfo(GoodInfo goodInfo) {
		return goodInfoDao.save(goodInfo);
	}
}
