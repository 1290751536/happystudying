package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;

import java.util.Date;
import java.util.List;

public interface UserDao {
    // 登录查询是否存在改用户
    public List<UserDomain> queryUser(String uNo, String uPassword);

    // 注册查询是否存在该老师或者学生
    public boolean isExistNo(String uNo);

    // 注册用户
    public boolean insertUser(String uNo, String uPassword) throws Exception;

    // 更新成为老师
    public void updateTeacherByNo(String uNo);

    // 更新用户名
    public void updateUserNameByNo(String uNo, String uName);

    // 更新性别
    public void updateUserSexByNo(String uNo, String uSex);

    // 填入注册时间
    public void updateUserRegisterTime(String uNo, Date uRegisterTime);

    // 更新用户邮箱
    public void updateUserEmailByNo(String uNo, String uEmail);

    // 更新用户所在地区
    public void updateUserAddressByNo(String uNo, String uAddress);

    // 更新用户签名
    public void updateUserSignatureByNo(String uNo, String uSignature);

    // 更新用户头像
    public void updateUserAvatar(String uNo, String uAvatar);

    // 更新用户密码
    public void updateUserPassword(String uNo, String uPassword);

    // 更新用户剩余钻石数
    public void updateUserDiamondNumber(String uNo, String uDiamondNumber);

    // 根据uId查找用户
    public List<UserDomain> queryUserById(String uId);
}
