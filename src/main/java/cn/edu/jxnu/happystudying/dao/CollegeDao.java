package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.CollegeDomain;

import java.util.List;

public interface CollegeDao {
    // 查询所有学院信息
    public List<CollegeDomain> queryAllCollege();
}
