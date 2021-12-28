package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.StudentDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplStudentDaoTest {
    StudentDao studentDao = new ImplStudentDao();
    @Test
    public void queryStudentByNo() {
        System.out.println(studentDao.queryStudentByNo("201926702046"));
    }
}