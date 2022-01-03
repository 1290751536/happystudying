package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.ActivityDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplActivityDao;
import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import cn.edu.jxnu.happystudying.service.ActivityService;

import java.util.List;

public class ImplActivityService implements ActivityService {
    ActivityDao activityDao = new ImplActivityDao();

    @Override
    public String insertActivity(ActivityDomain activityDomain) {
        return activityDao.insertActivity(activityDomain);
    }

    @Override
    public void updateCollegeById(String aId, String aCollege) {
        activityDao.updateCollegeById(aId, aCollege);
    }

    @Override
    public void updateGradeLevelById(String aId, String aGreadLevel) {
        activityDao.updateGradeLevelById(aId, aGreadLevel);
    }

    @Override
    public void updateIconById(String aId, String aIcon) {
        activityDao.updateIconById(aId, aIcon);
    }

    @Override
    public List<ActivityDomain> queryTopActivity() {
        return activityDao.queryTopActivity();
    }

    @Override
    public List<ActivityDomain> queryAllActivitySortByPublishTime() {
        return activityDao.queryAllActivitySortByPublishTime();
    }

    @Override
    public List<ActivityDomain> queryAllActivitySortByNumber() {
        return activityDao.queryAllActivitySortByNumber();
    }

    @Override
    public List<ActivityDomain> queryEssenceActivitySortByPublishTime() {
        return activityDao.queryEssenceActivitySortByPublishTime();
    }

    @Override
    public List<ActivityDomain> queryEssenceActivitySortByNumber() {
        return activityDao.queryEssenceActivitySortByNumber();
    }

    @Override
    public List<ActivityDomain> queryByKey(String key) {
        return activityDao.queryByKey("%" + key + "%");
    }

    @Override
    public List<ActivityDomain> queryById(String aId) {
        return activityDao.queryById(aId);
    }

    @Override
    public void updateViewNumberById(String aId, String x) {
        activityDao.updateViewNumberById(aId, x);
    }

    @Override
    public void updateResponsesNumberById(String aId, String x) {
        activityDao.updateResponsesNumberById(aId, x);
    }
}
