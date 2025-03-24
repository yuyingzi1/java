package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.Result;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserDao userDao;

    public List<User> getAll() {
        logger.info("获取所有用户信息");
        return userDao.getAll();
    }

    /**
     * 用户新增
     * @param user
     * @return
     */
    public User userRegister(User user) {
        logger.info("用户注册，手机号：{}", user.getPhone());
        String dbUserPhone = user.getPhone();
        User dbUser = userDao.findByName(dbUserPhone);
        if (ObjectUtil.isNotEmpty(dbUser)) {
            logger.error("用户已存在，手机号：{}", dbUserPhone);
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        userDao.insert(user);
        return user;
    }

    public User userLogin(User user) {
        logger.info("用户登录，手机号：{}", user.getPhone());
        String dbUserName = user.getPhone();
        User dbUser = userDao.findByName(dbUserName);
        if (ObjectUtil.isEmpty(dbUser)) {
            logger.error("用户不存在，手机号：{}", dbUserName);
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        String dbPassword = dbUser.getPassword();
        String userPassword = user.getPassword();
        if (!dbPassword.equals(userPassword)) { // 修正密码比较逻辑
            logger.error("密码错误，手机号：{}", dbUserName);
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return dbUser;
    }

    public User findById(Integer id) {
        logger.info("根据 ID 查询用户信息，ID：{}", id);
        return userDao.findById(id);
    }

    public void deleteById(Integer id) {
        logger.info("根据 ID 删除用户信息，ID：{}", id);
        userDao.deleteById(id);
    }

    public User userUpdate(User user) { // 移除 @RequestBody（服务层不应使用该注解）
        logger.info("更新用户信息，用户名：{}", user.getUserName());
        String userName = user.getUserName();
        if (ObjectUtil.isEmpty(userName)) {
            logger.error("用户名不能为空，更新失败");
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        userDao.update(user);
        return user;
    }

    public PageInfo<User> findPage(User search, Integer pageNum, Integer pageSize) {
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            logger.error("用户未登录，分页查询失败");
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> all = findByCondition(search);
        return PageInfo.of(all);
    }

    public List<User> findByCondition(User search) {
        logger.info("根据条件查询用户信息，条件：{}", search);
        return userDao.findBySearch(search);
    }
}