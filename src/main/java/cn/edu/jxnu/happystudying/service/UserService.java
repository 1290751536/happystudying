package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;

import java.util.Date;
import java.util.List;

public interface UserService {
    public List<UserDomain> login(String uNo, String uPassword);

    public boolean isExistNo(String uNo);

    public boolean register(String uNo, String uPassword) throws Exception;

    public void updateUserNameByNo(String uNo, String uName);

    public void setSex(String uNo, String uSex);

    public void setRegisterTime(String uNo, Date uRegisterTime);

    public void updateUserEmailByNo(String uNo,String uEmail);

    public void updateUserAddressByNo(String uNo,String uAddress);

    public void updateUserSignatureByNo(String uNo,String uSignature);

    public void updateUserAvatar(String uNo,String uAvatar);

    public void updeUserPassword(String uNo,String uPassword);
}
