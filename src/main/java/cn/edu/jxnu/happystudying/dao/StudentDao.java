package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.StudentDomain;

import java.util.List;

public interface StudentDao {
    // 根据学号查询学生信息
    public List<StudentDomain> queryStudentByNo(String uNo);
}
