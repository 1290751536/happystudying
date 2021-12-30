package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import org.junit.Test;

import java.util.Date;

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

    @Test
    public void updateUserSexByNo() {
        userDao.updateUserSexByNo("201926702038", "女");
    }

    @Test
    public void updateUserRegisterTime() {
        userDao.updateUserRegisterTime("201926702038", new Date());
    }

    @Test
    public void updateUserEmailByNo() {
        userDao.updateUserEmailByNo("201926702046", "ls1290751536@163.com");
    }

    @Test
    public void updateUserAddressByNo() {
        userDao.updateUserAddressByNo("201926702046", "江西南昌");
    }

    @Test
    public void updateUserSignatureByNo() {
        userDao.updateUserSignatureByNo("201926702046", "我是一个快乐的小白");
    }
}