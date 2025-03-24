package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色用户父类
 */
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "userName")
    private String userName;
    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 性别
     */
    @Column(name = "gender")
    private String gender;

    /**
     * 角色标识
     */
    @Column(name = "role")
    private Integer role;
    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private Integer status;

}
