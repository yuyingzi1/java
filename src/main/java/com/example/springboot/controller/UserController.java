package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
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

    /**
     * 新增用户接口
     */
    @PostMapping
    public Result userRegister(@RequestBody User user){

        return Result.success(userService.userRegister(user));


    }
    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody User search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(userService.findPage(search, pageNum, pageSize));
    }


    /**
     * 编辑用户信息接口
     */
    @PutMapping("/update")
    public Result userEdit(@RequestBody User user){

        return Result.success(userService.userUpdate(user));

    }




}