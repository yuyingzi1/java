package com.example.springboot.service;

import com.example.springboot.dao.MerchantsDao;
import com.example.springboot.entity.Merchants;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantsServe {

    @Resource
    MerchantsDao merchantsDao;
    public List<Merchants> getAll() {

        return merchantsDao.getAll();
    }
}
