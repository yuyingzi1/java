package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.common.ResultCode;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.exception.CustomException;
import com.example.springboot.service.UserServe;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserServe userService;

    @GetMapping("/alldata")
    public Result getData(){

        List<User> Users = userService.getAll();
        if (Users.size() < 1){
            throw new CustomException(ResultCode.DATA_LESS);
        }
        return Result.success(Users);

    }

    /**
     * 注册接口
     */

    @PostMapping("/register")
    public Result userRegister(@RequestBody User user){

        return Result.success(userService.userRegister(user));

    }

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody User user){

        return Result.success(userService.userLogin(user));

    }
}
