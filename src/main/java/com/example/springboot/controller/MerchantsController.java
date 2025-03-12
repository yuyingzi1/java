package com.example.springboot.controller;

import com.example.springboot.entity.Merchants;
import com.example.springboot.service.MerchantsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/merchants")
public class MerchantsController {
    @Resource
    MerchantsService merchantsServe;
    @GetMapping("/alldata")
    public List<Merchants> list() {
        return merchantsServe.getAll();
    }
}
