package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.TeacherDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplTeacherDaoTest {
    TeacherDao teacherDao = new ImplTeacherDao();

    @Test
    public void queryTeacherByNo() {
        System.out.println(teacherDao.queryTeacherByNo("100000"));
    }
}