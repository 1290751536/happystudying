package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.TeacherDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplTeacherDao;
import cn.edu.jxnu.happystudying.domain.TeacherDomain;
import cn.edu.jxnu.happystudying.service.TeacherService;

import java.util.List;

public class ImplTeacherService implements TeacherService {
    private TeacherDao teacherDao = new ImplTeacherDao();

    @Override
    public List<TeacherDomain> queryTeacherByNo(String uNo) {
        return teacherDao.queryTeacherByNo(uNo);
    }
}
