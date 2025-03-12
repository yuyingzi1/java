package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;



    //    查询所有数据
    @GetMapping("/alldata")
    public Result getData(){

        return Result.success(userService.getAll());

    }



}