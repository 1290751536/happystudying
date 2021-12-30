package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.UserMessageDomain;

import java.util.List;

public interface MessageService {
    public List<UserMessageDomain> queryUserMessageById(String uId);

    public void deleteMessageById(String mId);

    public void deleteMessageByUserId(String uId);
}
