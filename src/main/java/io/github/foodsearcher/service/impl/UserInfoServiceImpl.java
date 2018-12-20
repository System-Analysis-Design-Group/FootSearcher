package io.github.foodsearcher.service.impl;

import io.github.foodsearcher.dao.UserInfoDao;
import io.github.foodsearcher.service.UserInfoService;
import io.github.foodsearcher.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;


/**
 * Created by young on 18-3-11.
 */

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }
}
