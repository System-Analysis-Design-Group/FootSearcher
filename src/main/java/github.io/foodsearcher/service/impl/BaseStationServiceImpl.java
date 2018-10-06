package top.yaoyongdou.service.impl;

import org.springframework.stereotype.Service;
import top.yaoyongdou.dao.BaseStationDao;
import top.yaoyongdou.service.BaseStationService;
import top.yaoyongdou.model.BaseStation;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by young on 18-3-11.
 */

@Service
@Transactional
public class BaseStationServiceImpl implements BaseStationService {

    @Resource
    private BaseStationDao baseStationDao;


    @Override
    public BaseStation findByNodename(String nodename) {
        return baseStationDao.findByNodename(nodename);
    }

    @Override
    public List<BaseStation> findAllNode() {
        LinkedList<BaseStation> baseStationLinkedList = new LinkedList<>();
        Iterator<BaseStation> iterator = baseStationDao.findAll().iterator();
        while (iterator.hasNext()) {
            baseStationLinkedList.add(iterator.next());
        }
        return baseStationLinkedList;
    }
}
