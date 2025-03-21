package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    //    查询所有数据
    @GetMapping("/alldata")
    public Result getData(){

        return Result.success(adminService.getAll());

    }

    /**
     *新增维修员接口
     */
    @PostMapping
    public Result addRegister(@RequestBody Admin admin){
        return Result.success(adminService.adminRegister(admin));
    }

    /**
     * 删除维修员接口
     */
    @DeleteMapping("/{id}")
    public Result deleteRegister(@PathVariable("id") Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改维修员接口
     */
    @PutMapping("/update")
    public Result adminUpdate(@RequestBody Admin admin){

        return Result.success(adminService.adminUpdate(admin));

    }
    /**
     * 分页查询接口
     */
    @PostMapping("/page")
    public Result page(@RequestBody Admin search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize){
        return Result.success(adminService.page(search,pageNum,pageSize));
    }


}