package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.CheckInDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplCheckInDao;
import cn.edu.jxnu.happystudying.domain.CheckInDomain;
import cn.edu.jxnu.happystudying.service.CheckInService;

import java.util.Date;
import java.util.List;

public class ImplCheckInService implements CheckInService {
    CheckInDao checkInDao = new ImplCheckInDao();

    @Override
    public List<CheckInDomain> queryCheckInById(String cId) {
        return checkInDao.queryCheckInById(cId);
    }

    @Override
    public void insertUserId(String cId) {
        checkInDao.insertUserId(cId);
    }

    @Override
    public void changeZero(String cId) {
        checkInDao.changeZero(cId);
    }

    @Override
    public void updateTodayData(String cId, Date today) {
        checkInDao.updateTodayData(cId, today);
    }
}
