package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user")
public class User extends Account {
    @Column(name = "email")
    private String email;

    @Column(name = "shipping_address")
    private String shipping_address;
}