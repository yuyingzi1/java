package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.AdminDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Resource
    AdminDao adminDao;

    public List<Admin> getAll() {
        logger.info("获取所有管理员信息");
        return adminDao.getAll();
    }

    public Admin adminLogin(Admin admin) {
        logger.info("管理员登录，用户名：{}", admin.getPhone());
        // 1.拿到用户传来的用户名，先到数据库查看这个用户名是否存在,如果不存在抛出异常：“用户不存在”
        Admin dbAdmin = adminDao.findByName(admin.getPhone());
        if (ObjectUtil.isEmpty(dbAdmin)) {
            logger.error("用户不存在，用户名：{}", admin.getPhone());
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        // 2.如果用户存在，则那用户输入的密码跟数据库找到的用户的密码比对，密码输入正确，允许登录
        String password = admin.getPassword(); // 用户输入的密码
        String dbPassword = dbAdmin.getPassword(); // 数据库找到用户的密码

        if (!password.equalsIgnoreCase(dbPassword)) {
            logger.error("密码错误，用户名：{}", admin.getPhone());
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return dbAdmin;
    }

    public Admin findById(Integer id) {
        logger.info("根据 ID 查询管理员信息，ID：{}", id);
        return adminDao.findById(id);
    }

    public Admin adminRegister(Admin admin) {
        logger.info("管理员注册，用户名：{}", admin.getUserName());
        String userName = admin.getUserName();
        // 1.判断用户名是否为空
        if (ObjectUtil.isEmpty(userName)) {
            logger.error("用户名不能为空，注册失败");
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }
        // 2.拿到用户传来的用户名，先到数据库查看这个用户名是否存在,如果存在抛出异常：“用户名已存在”
        Admin dbAdmin = adminDao.findByName(userName);
        if (ObjectUtil.isNotEmpty(dbAdmin)) {
            logger.error("用户名已存在，注册失败，用户名：{}", userName);
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            // 如果密码为空，设置默认密码
            admin.setPassword("123456");
        }
        adminDao.insert(admin);
        return admin;
    }

    public void deleteById(Integer id) {
        logger.info("根据 ID 删除管理员信息，ID：{}", id);
        adminDao.deleteById(id);
    }

    public Admin adminUpdate(Admin admin) {
        logger.info("更新管理员信息，用户名：{}", admin.getUserName());
        String userName = admin.getUserName();
        // 1.判断用户名是否为空
        if (ObjectUtil.isEmpty(userName)) {
            logger.error("用户名不能为空，更新失败");
            throw new CustomException(ResultCode.USERNAME_ISNULL);
        }

        adminDao.update(admin);
        return admin;
    }

    // 分页查询和模糊查询的方法
    public PageInfo<Admin> page(Admin search, Integer pageNum, Integer pageSize) {
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            logger.error("用户未登录，分页查询失败");
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> all = findByCondition(search);

        return PageInfo.of(all);
    }

    // 根据用户名条件查询的方法
    public List<Admin> findByCondition(Admin search) {
        logger.info("根据条件查询管理员信息，条件：{}", search);
        return adminDao.findBySearch(search);
    }

//    public void statusOrStop(Integer status, Integer id) {
//
//    }
}