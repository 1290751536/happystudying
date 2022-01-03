package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.ActivityDao;
import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ImplActivityDaoTest {
    ActivityDao activityDao = new ImplActivityDao();

    @Test
    public void insertActivity() {
        ActivityDomain activityDomain = new ActivityDomain();
        activityDomain.setaUserId("123");
        activityDomain.setaUserName("李弟平");
        activityDomain.setaPublishTime(new Date());
        activityDomain.setaBeginTime(new Date());
        activityDomain.setaEndTime(new Date());
        activityDomain.setaTitle("工作室");
        activityDomain.setaDescription("马上就要答辩了大家做好准备！");
        System.out.println(activityDao.insertActivity(activityDomain));
    }

    @Test
    public void updateCollegeById() {
        activityDao.updateCollegeById("10000002", "音乐学院");
    }

    @Test
    public void updateGradeLevelById() {
        activityDao.updateGradeLevelById("10000002", "2019级");
    }

    @Test
    public void queryTopActivity() {
        System.out.println(activityDao.queryTopActivity());
    }

    @Test
    public void queryByKey() {
        System.out.println(activityDao.queryByKey("%工作%"));
    }

    @Test
    public void updateViewNumberById() {
        activityDao.updateViewNumberById("10000008", "-30");
    }

    @Test
    public void updateResponsesNumberById() {
        activityDao.updateResponsesNumberById("10000008", "-10");
    }
}