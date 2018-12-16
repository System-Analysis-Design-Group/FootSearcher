package io.github.foodsearcher.dao;


import io.github.foodsearcher.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by young on 18-3-11.
 */
public interface UserInfoDao  extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

    public UserInfo save(UserInfo userInfo);
}
