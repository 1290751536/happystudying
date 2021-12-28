package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.StudentDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplStudentDao;
import cn.edu.jxnu.happystudying.domain.StudentDomain;
import cn.edu.jxnu.happystudying.service.StudentService;

import java.util.List;

public class ImplStudentService implements StudentService {
    private StudentDao studentDao = new ImplStudentDao();
    @Override
    public List<StudentDomain> queryStudentByNo(String uNo) {
        return studentDao.queryStudentByNo(uNo);
    }
}
