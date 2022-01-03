package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.MessageDao;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
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

    @Test
    public void insertMessage() {
        messageDao.insertMessage(new UserMessageDomain());
    }
}