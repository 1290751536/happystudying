package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.CheckInDomain;

import java.util.Date;
import java.util.List;

public interface CheckInService {
    public List<CheckInDomain> queryCheckInById(String cId);

    public void insertUserId(String cId);

    public void changeZero(String cId);

    public void updateTodayData(String cId, Date today);
}
