package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.QuestionDomain;

import java.util.List;

public interface QuestionDao {
    // 插入一条新问答，返回问答号做为主键
    public String insertQuestion(QuestionDomain questionDomain);

    // 根据id，更新活动图标
    public void updateIconById(String qId, String qIcon);

    // 查询所有置顶的问答
    public List<QuestionDomain> queryTopQuestion();

    // 查询所有问答根据发布时间降序排序
    public List<QuestionDomain> queryAllQuestionSortByPublishTime();

    // 查询所有问答根据回复数量+浏览数降序
    public List<QuestionDomain> queryAllQuestionSortByNumber();

    // 查询所有精华贴根据发布时间降序排序
    public List<QuestionDomain> queryEssenceQuestionSortByPublishTime();

    // 查询所有精华贴根据回复数量+浏览数降序排序
    public List<QuestionDomain> queryEssenceQuestionSortByNumber();

    // 根据关键字查找活动
    public List<QuestionDomain> queryByKey(String key);

    // 让一个问答浏览量+x
    public void updateViewNumberById(String qId, String x);

    // 让一个问答回复数+x
    public void updateResponsesNumberById(String qId, String x);

    // 根据id找到一个问答
    public List<QuestionDomain> queryById(String qId);

    // 根据id更新一个问答信息
    public void updateQuestionById(QuestionDomain questionDomain);

    // 根据用户id查找到全部的提问信息
    public List<QuestionDomain> queryAllQuestionByUserId(String qUserId);
}
