package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.CollegeDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplCollegeDao;
import cn.edu.jxnu.happystudying.domain.CollegeDomain;
import cn.edu.jxnu.happystudying.service.CollegeService;

import java.util.List;

public class ImplCollegeService implements CollegeService {
    CollegeDao collegeDao = new ImplCollegeDao();
    @Override
    public List<CollegeDomain> queryAllCollege() {
        return collegeDao.queryAllCollege();
    }
}
