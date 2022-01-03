package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.CollegeDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplCollegeDaoTest {
    CollegeDao collegeDao = new ImplCollegeDao();
    @Test
    public void queryAllCollege() {
        System.out.println(collegeDao.queryAllCollege());
    }
}