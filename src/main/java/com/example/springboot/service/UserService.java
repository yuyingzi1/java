package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.User;
import com.example.springboot.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User userRegister(User user) {
        // 拿用户输入的用户信息，去校验数据库中是否存在，存在则抛出异常
        String dbUserName = user.getUserName();
        User dbUser = userDao.findByName(dbUserName);
        // 如果存在，则将抛出异常提示用户已存在
        if (ObjectUtil.isNotEmpty(dbUser)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        // 如果不存在，则将用户信息插入数据库
        userDao.insert(user);

        // 这里可以根据业务需求返回相应的结果，例如返回插入的用户信息
        return user;
    }

    public User userLogin(User user) {
        String dbUserName = user.getUserName();
        User dbUser = userDao.findByName(dbUserName);
        if (ObjectUtil.isEmpty(dbUser)) {
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        //数据库密码
        String dbPassword = dbUser.getPassword();
        //用户输入密码
        String userPassword = user.getPassword();
        if (!dbUser.getPassword().equals(dbPassword)) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return dbUser;
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }
}