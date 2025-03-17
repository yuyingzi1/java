package com.example.springboot.dao;

import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    List<Admin> getAll();

    Admin findByName(@Param("userName") String userName);

    Admin findById(@Param("id") Integer id);

    void insert(Admin admin);

    void deleteById(Integer id);

    void update(Admin admin);
}
