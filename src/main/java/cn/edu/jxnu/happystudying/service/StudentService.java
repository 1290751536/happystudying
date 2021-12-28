package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.StudentDomain;

import java.util.List;

public interface StudentService {
    public List<StudentDomain> queryStudentByNo(String uNo);
}
