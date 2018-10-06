package top.yaoyongdou.service.impl;

import org.springframework.stereotype.Service;
import top.yaoyongdou.dao.UserInfoDao;
import top.yaoyongdou.service.UserInfoService;
import top.yaoyongdou.shiro.UserInfo;

import javax.annotation.Resource;
import javax.transaction.Transactional;


/**
 * Created by young on 18-3-11.
 */

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
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
