package com.example.springboot.dao;

import com.example.springboot.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    List<Notice> getAll();

    void insert(Notice notice);
    

    void deleteById(Integer id);

    void update(Notice notice);

    List<Notice> findBySearch(@Param("search") Notice search);
}
