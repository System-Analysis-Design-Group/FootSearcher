package top.yaoyongdou.dao;

import org.springframework.data.repository.CrudRepository;
import top.yaoyongdou.model.BaseStation;

/**
 * Created by young on 18-3-17.
 */
public interface BaseStationDao extends CrudRepository<BaseStation,Long> {
    public BaseStation findByNodename(String nodename);
}
