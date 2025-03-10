package com.example.springboot.dao;

import com.example.springboot.entity.Merchants;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantsDao {
    List<Merchants> getAll();
}
