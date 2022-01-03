package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.CheckInDao;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ImplCheckInDaoTest {
    CheckInDao checkInDao = new ImplCheckInDao();

    @Test
    public void queryCheckInById() {
        System.out.println(checkInDao.queryCheckInById("10000000"));
        System.out.println(checkInDao.queryCheckInById("10000001"));
    }

    @Test
    public void changeZero() {
        checkInDao.changeZero("1000001");
    }

    @Test
    public void updateTodayData() {
        checkInDao.updateTodayData("10000012",new Date());
    }
}