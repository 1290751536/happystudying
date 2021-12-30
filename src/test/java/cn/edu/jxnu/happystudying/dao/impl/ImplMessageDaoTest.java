package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.MessageDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplMessageDaoTest {
    MessageDao messageDao = new ImplMessageDao();

    @Test
    public void deleteMessageById() {
        messageDao.deleteMessageById("10000007");
    }

    @Test
    public void deleteMessageByUserId() {
        messageDao.deleteMessageByUserId("2");
    }
}