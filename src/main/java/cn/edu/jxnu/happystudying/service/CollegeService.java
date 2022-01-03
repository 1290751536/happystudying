package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.CollegeDomain;

import java.util.List;

public interface CollegeService {
    // 查询所有学院信息
    public List<CollegeDomain> queryAllCollege();
}
