package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplUserDaoTest {
    UserDao userDao = new ImplUserDao();

    @Test
    public void queryUser() {
        System.out.println(userDao.queryUser("100001", "202CB962AC59075B964B07152D234B70"));
    }

    @Test
    public void isExistNo() {
        System.out.println(userDao.isExistNo("123"));
    }

    @Test
    public void insertUser() {
        try {
            System.out.println(userDao.insertUser("201926702049", "123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTeacherByNo() {
        userDao.updateTeacherByNo("100000");
    }

    @Test
    public void updateUserNameByNo() {
        userDao.updateUserNameByNo("201926702046", "刘晟");
    }
}