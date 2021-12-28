package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.UserDomain;

import java.util.List;

public interface UserService {
    public List<UserDomain> login(String uNo, String uPassword);

    public boolean isExistNo(String uNo);

    public boolean register(String uNo,String uPassword) throws Exception;

    public void updateUserNameByNo(String uNo,String uName);
}
