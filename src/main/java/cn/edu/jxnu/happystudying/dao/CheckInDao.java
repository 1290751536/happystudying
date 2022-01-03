package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.CheckInDomain;

import java.util.Date;
import java.util.List;

public interface CheckInDao {
    // 根据用户id查找用户的签到信息
    public List<CheckInDomain> queryCheckInById(String cId);

    // 插入用户id
    public void insertUserId(String cId);

    // 连续签到天数清零
    public void changeZero(String cId);

    // 更新今天的签到数据
    public void updateTodayData(String cId, Date today);
}
