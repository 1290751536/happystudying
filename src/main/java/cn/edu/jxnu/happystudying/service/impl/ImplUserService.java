package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplUserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;

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
}
