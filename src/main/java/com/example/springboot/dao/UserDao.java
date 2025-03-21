package com.example.springboot.dao;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAll();

    User findByName(@Param("phone") String phone);

    void insert(User user);

    User findById(@Param("id") Integer id);
}
