package top.yaoyongdou.dao;

import org.springframework.data.repository.CrudRepository;
import top.yaoyongdou.shiro.UserInfo;

/**
 * Created by young on 18-3-11.
 */
public interface UserInfoDao  extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

    public UserInfo save(UserInfo userInfo);
}
