package io.github.foodsearcher.service;

import io.github.foodsearcher.model.UserInfo;

/**
 * Created by young on 18-3-11.
 */
public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

    public UserInfo createUserInfo(UserInfo userInfo);
}
