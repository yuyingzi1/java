package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.dao.AdminDao;
import com.example.springboot.dao.MerchantsDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AdminDao adminDao;

    @Resource
    UserDao userDao;

    @Resource
    MerchantsDao merchantsDao;

    /**
     * 注册接口
     */


    @PostMapping("/register")
    public Result accountRegister(@RequestBody Account account) {
        Integer role = account.getRole();
        //用户注册
        if (role == 2) {
            User user = new User();
            BeanUtils.copyProperties(account, user);
        }
        return Result.success();
    }

}
