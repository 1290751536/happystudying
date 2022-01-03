package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplUserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
import cn.edu.jxnu.happystudying.service.UserService;

import java.util.Date;
import java.util.List;

public class ImplUserService implements UserService {
    private UserDao userDao = new ImplUserDao();

    @Override
    public List<UserDomain> login(String uNo, String uPassword) {
        return userDao.queryUser(uNo, uPassword);
    }

    @Override
    public boolean isExistNo(String uNo) {
        return userDao.isExistNo(uNo);
    }

    @Override
    public boolean register(String uNo, String uPassword) throws Exception {
        return userDao.insertUser(uNo, uPassword);
    }

    @Override
    public void updateUserNameByNo(String uNo, String uName) {
        userDao.updateUserNameByNo(uNo, uName);
    }

    @Override
    public void updateTeacherByNo(String uNo) {
        userDao.updateTeacherByNo(uNo);
    }

    @Override
    public void setSex(String uNo, String uSex) {
        userDao.updateUserSexByNo(uNo, uSex);
    }

    @Override
    public void setRegisterTime(String uNo, Date uRegisterTime) {
        userDao.updateUserRegisterTime(uNo, uRegisterTime);
    }

    @Override
    public void updateUserEmailByNo(String uNo, String uEmail) {
        userDao.updateUserEmailByNo(uNo, uEmail);
    }

    @Override
    public void updateUserAddressByNo(String uNo, String uAddress) {
        userDao.updateUserAddressByNo(uNo, uAddress);
    }

    @Override
    public void updateUserSignatureByNo(String uNo, String uSignature) {
        userDao.updateUserSignatureByNo(uNo, uSignature);
    }

    @Override
    public void updateUserAvatar(String uNo, String uAvatar) {
        userDao.updateUserAvatar(uNo, uAvatar);
    }

    @Override
    public void updeUserPassword(String uNo, String uPassword) {
        userDao.updateUserPassword(uNo, uPassword);
    }

    @Override
    public void updateUserDiamondNumber(String uNo, String uDiamondNumber) {
        userDao.updateUserDiamondNumber(uNo, uDiamondNumber);
    }

    @Override
    public List<UserDomain> queryUserById(String uId) {
        return userDao.queryUserById(uId);
    }
}
