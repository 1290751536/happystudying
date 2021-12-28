package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.UserDomain;

import java.util.List;

public interface UserDao {
    // 登录查询是否存在改用户
    public List<UserDomain> queryUser(String uNo, String uPassword);

    // 注册查询是否存在该老师或者学生
    public boolean isExistNo(String uNo);

    // 注册用户
    public boolean insertUser(String uNo,String uPassword) throws Exception;

    // 更新成为老师
    public void updateTeacherByNo(String uNo);

    // 更新用户名
    public void updateUserNameByNo(String uNo,String uName);
}
