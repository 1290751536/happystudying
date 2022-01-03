package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.ActivityDomain;

import java.util.List;

public interface ActivityDao {
    // 插入一条新活动，返回活动号做为主键
    public String insertActivity(ActivityDomain activityDomain);

    // 根据活动id更新所属学院
    public void updateCollegeById(String aId, String aCollege);

    // 根据活动id更新所属年级
    public void updateGradeLevelById(String aId, String aGreadLevel);

    // 根据活动id更新活动图标
    public void updateIconById(String aId, String aIcon);

    // 查询所有置顶的活动
    public List<ActivityDomain> queryTopActivity();

    // 查询所有活动根据发布时间降序排序
    public List<ActivityDomain> queryAllActivitySortByPublishTime();

    // 查询所有活动根据回复数量+浏览数降序排序
    public List<ActivityDomain> queryAllActivitySortByNumber();

    // 查询所有精华贴根据发布时间降序排序
    public List<ActivityDomain> queryEssenceActivitySortByPublishTime();

    // 查询所有精华贴根据回复数量+浏览数降序排序
    public List<ActivityDomain> queryEssenceActivitySortByNumber();

    // 根据关键字查找活动
    public List<ActivityDomain> queryByKey(String key);

    // 根据id查找一个活动
    public List<ActivityDomain> queryById(String aId);

    // 让一个活动浏览量+x
    public void updateViewNumberById(String aId,String x);

    // 让一个活动回复数+x
    public void updateResponsesNumberById(String aId,String x);
}
