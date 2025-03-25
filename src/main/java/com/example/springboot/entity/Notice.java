package com.example.springboot.entity;

import lombok.Data;

/**
 * 系统公告信息
 */
@Data
public class Notice {

    private String id;
    private String title;
    private String content;
    private String createTime;
}
