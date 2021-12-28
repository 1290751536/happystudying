package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.TeacherDomain;

import java.util.List;

public interface TeacherDao {
    // 找寻注册的用户是否是一位老师
    public List<TeacherDomain> queryTeacherByNo(String uNo);
}
