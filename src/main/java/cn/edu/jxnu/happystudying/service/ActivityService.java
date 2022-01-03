package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.ActivityDomain;

import java.util.List;

public interface ActivityService {
    public String insertActivity(ActivityDomain activityDomain);

    public void updateCollegeById(String aId, String aCollege);

    public void updateGradeLevelById(String aId, String aGreadLevel);

    public void updateIconById(String aId, String aIcon);

    public List<ActivityDomain> queryTopActivity();

    public List<ActivityDomain> queryAllActivitySortByPublishTime();

    public List<ActivityDomain> queryAllActivitySortByNumber();

    public List<ActivityDomain> queryEssenceActivitySortByPublishTime();

    public List<ActivityDomain> queryEssenceActivitySortByNumber();

    public List<ActivityDomain> queryByKey(String key);

    public List<ActivityDomain> queryById(String aId);

    public void updateViewNumberById(String aId,String x);

    public void updateResponsesNumberById(String aId,String x);
}
