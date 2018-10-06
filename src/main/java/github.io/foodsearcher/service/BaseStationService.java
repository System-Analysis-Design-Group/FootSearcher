package top.yaoyongdou.service;

import top.yaoyongdou.model.BaseStation;

import java.util.List;

/**
 * Created by young on 18-3-17.
 */
public interface BaseStationService {
    public BaseStation findByNodename(String nodename);

    public List<BaseStation> findAllNode();
}
