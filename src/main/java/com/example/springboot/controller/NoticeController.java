package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;


    //    查询所有数据
    @GetMapping("/alldata")
    public Result getData(){

        return Result.success(noticeService.getAll());

    }

    /**
     * 新增用户接口
     */
    @PostMapping
    public Result noticeRegister(@RequestBody Notice notice){

        return Result.success(noticeService.noticeRegister(notice));


    }
    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Notice search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(noticeService.findPage(search, pageNum, pageSize));
    }


    /**
     * 编辑用户信息接口
     */
    @PutMapping("/update")
    public Result noticeEdit(@RequestBody Notice notice){

        return Result.success(noticeService.noticeUpdate(notice));

    }




}