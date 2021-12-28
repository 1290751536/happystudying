package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.TeacherDomain;

import java.util.List;

public interface TeacherService {

    public List<TeacherDomain> queryTeacherByNo(String uNo);
}
