package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.UserMessageDomain;

import java.util.List;

public interface MessageDao {
    // 查询该用户的全部消息
    public List<UserMessageDomain> queryUserMessageById(String uId);

    // 删除一条消息
    public void deleteMessageById(String mId);

    // 删除某个用户下的全部消息
    public void deleteMessageByUserId(String uId);

    // 插入一条新消息
    public void insertMessage(UserMessageDomain userMessageDomain);
}
