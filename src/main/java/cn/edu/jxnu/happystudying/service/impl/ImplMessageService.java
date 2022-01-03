package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.MessageDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplMessageDao;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
import cn.edu.jxnu.happystudying.service.MessageService;

import java.util.List;

public class ImplMessageService implements MessageService {
    MessageDao messageDao = new ImplMessageDao();

    @Override
    public List<UserMessageDomain> queryUserMessageById(String uId) {
        return messageDao.queryUserMessageById(uId);
    }

    @Override
    public void deleteMessageById(String mId) {
        messageDao.deleteMessageById(mId);
    }

    @Override
    public void deleteMessageByUserId(String uId) {
        messageDao.deleteMessageByUserId(uId);
    }

    @Override
    public void insertMessage(UserMessageDomain userMessageDomain) {
        messageDao.insertMessage(userMessageDomain);
    }
}
